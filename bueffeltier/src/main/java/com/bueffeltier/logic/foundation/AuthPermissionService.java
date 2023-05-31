package com.bueffeltier.logic.foundation;

import java.math.BigInteger;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;

import com.bueffeltier.crosscutting.AppPropertyService;
import com.bueffeltier.data.jdbc.UserJDBC;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;

public class AuthPermissionService
{
	private static AuthPermissionService instance;

	private UserRepository userRepository = UserRepository.getInstance();

	private static NewEmailService newEmailService;

	private AppPropertyService appPropertyService = AppPropertyService
	    .getInstance();

	private AuthPermissionService()
	{
		try
		{
			newEmailService = NewEmailService.getInstance();

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static AuthPermissionService getInstance()
	{
		if (instance == null)
		{
			instance = new AuthPermissionService();
		}
		return instance;
	}

	/**
	 * Erstmalige User-Registrierung. Bis zum Register-Confirm erhält das Konto
	 * einen Activation-Key mit Verfallsdatum und das Konto wird auf active =
	 * false gesetzt.
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * @return
	 */
	public boolean register(
	    HttpServletRequest request,
	    String name,
	    String email,
	    String password,
	    boolean isGuestSession
	)
	{
		/*
		 * Methode hat keinen Einfluss auf die aktuelle Session.
		 */

		boolean nameExists = userRepository.nameExists(name);
		boolean emailExists = userRepository.emailExists(email);

		if (nameExists || emailExists)
		{
			// TODO sveng 28.01.2023: fehlerseite oder fehlermeldung
			return false;

		} else
		{
			String generatedSecuredPasswordHash = "";

			try
			{
				generatedSecuredPasswordHash = generatePasswordHash(password);

			} catch (NoSuchAlgorithmException | InvalidKeySpecException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();

				// TODO sveng 28.01.2023: fehlerseite oder fehlermeldung

				return false;
			}

			// TODO sveng 27.01.2023: try/catch
			UserJDBC user = createUserForRegistration(
			    name, email, generatedSecuredPasswordHash
			);

			// TODO sveng 27.01.2023: homepage path aus pageRepo beziehen? oder
			// "/"
			String aktivationLink = appPropertyService.getHostName()
			    + appPropertyService.getServletHomePagePath()
			    + appPropertyService.getRegisterConfirmPageHtmlTitle()
			    + "?activate=" //
			    + user.getActivationKey();

			try
			{
				newEmailService.sendMail(
				    appPropertyService.getAccountActivationMailSubject(),
				    "Hallo " + name + "\n" + "\n"
				        + appPropertyService.getAccountActivationMailMessage1()
				        + "\n"
				        + appPropertyService.getAccountActivationMailMessage2()
				        + "\n" + aktivationLink,
				    email
				);
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();

				return false;
			}

			// TODO sveng 27.01.2023: try/catch
			long dbId = userRepository.write(user);
			if (dbId > -1)
			{
				user.setId(dbId);
				request.setAttribute("user", user);
			}

			return true;
		}
	}

	/**
	 * Aktiviert User-Account, wenn es einen User zum Activation-Key gibt und
	 * wenn der Activation-Key noch nicht abgelaufen ist.
	 */
	public boolean
	    activateAccount(HttpServletRequest request, String activationKey)
	{
		/*
		 * Methode hat keinen Einfluss auf die aktuelle Session.
		 */

		UserJDBC user = userRepository.readByActivationKey(activationKey);

		if (user != null)
		{
			boolean isActivationKeyValid = isActivationKeyValid(
			    activationKey, user.getActivationKeyExpirationTime()
			);

			if (isActivationKeyValid)
			{
				user.setActive(true);
				user.setActivationKeyExpirationTime(null);
				user.setActivationKey(null);

				userRepository.update(user);
				request.setAttribute("user", user);

				return true;
			}
		}
		return false;
	}

	private boolean isActivationKeyValid(
	    String activationKey,
	    LocalDateTime activationKeyExperiationTime
	)
	{
		if (LocalDateTime.now().isAfter(activationKeyExperiationTime))
		{
			return false;
		}

		return true;
	}

	/**
	 * Login ist erforderlich, wenn Request kein oder kein valides Session-Token
	 * hat.
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean
	    login(HttpServletRequest request, String userName, String password)
	{
		UserJDBC oldUser = (UserJDBC) request.getAttribute("user");

		UserJDBC newUser;

		try
		{
			newUser = userRepository.read(userName);

		} catch (Exception e)
		{
			// TODO sveng 28.01.2023: fehlerseite? fehlermessage?
			return false;
		}

		if (newUser != null && newUser.isActive())
		{
			boolean isPasswordValid = false;

			try
			{
				isPasswordValid = isPasswordValid(
				    password, newUser.getPasswordHash()
				);

			} catch (NoSuchAlgorithmException | InvalidKeySpecException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();

				// TODO sveng 28.01.2023: fehlerseite? fehlermessage?
				return false;
			}

			if (isPasswordValid)
			{
				if (!isUserLoggedIn(request, newUser))
				{
					// logout old user:

					// TODO sveng 24.05.2023: löschen
//					request.setAttribute("sessionToken", null);
					request.setAttribute("sessionId", null);
					request.setAttribute("permission", 0);
					request.setAttribute("user", null);

					// login registered user:

					String sessionId = UUID.randomUUID().toString();

					// TODO sveng 24.05.2023: löschen
//					String jwt = createWebtoken(
//					    request, sessionId, newUser.getName()
//					);
//					newUser.setSessionToken(jwt);

					newUser.setSessionId(sessionId);
					// TODO sveng 28.01.2023: try/catch
					userRepository.update(newUser);

					// TODO sveng 24.05.2023: löschen
//					request.setAttribute("sessionToken", jwt);
					request.setAttribute("sessionId", sessionId);
					request.setAttribute("permission", newUser.getPermission());
					request.setAttribute("user", newUser);

					// save delete old user:

					if (oldUser != null)
					{
						if (oldUser.isAnonymous())
						{
							userRepository.delete(oldUser);

						} else
						{
							oldUser.setSessionId(null);
							oldUser.setSessionToken(null);

							userRepository.update(oldUser);
						}
					}

				}
				// TODO sveng 28.01.2023: fehlerseite? fehlermessage?
				return true;

			} else
			{
				// TODO sveng 28.01.2023: fehlerseite? fehlermessage?
				return false;
			}
		} else
		{
			// TODO sveng 28.01.2023: fehlerseite? fehlermessage?
			return false;
		}
	}

	public boolean isUserLoggedIn(HttpServletRequest request, UserJDBC user)
	{
		UserJDBC userFromCurrentRequest = (UserJDBC) request
		    .getAttribute("user");

		boolean isUserFromCurrentRequest;

		if (userFromCurrentRequest == null)
		{
			isUserFromCurrentRequest = false;

		} else
		{
			if (userFromCurrentRequest.getId() == user.getId())
			{
				isUserFromCurrentRequest = true;

			} else
			{
				isUserFromCurrentRequest = false;
			}
		}

		int permissionFromCurrentRequest = (int) request
		    .getAttribute("permission");

		boolean isUserLoggedIn = permissionFromCurrentRequest > 0;

		if (isUserFromCurrentRequest && isUserLoggedIn)
		{
			return true;
		}

		return false;
	}

	public boolean logout(HttpServletRequest request)
	{
		UserJDBC user;

		try
		{
			user = (UserJDBC) request.getAttribute("user");

		} catch (Exception e)
		{
			// TODO sveng 28.01.2023: fehlerseite: logout fehlgeschlagen.
			return false;
		}

		if (isUserLoggedIn(request, user))
		{
			// logout old user:

			user.setSessionToken(null);
			user.setSessionId(null);
			userRepository.update(user);

			// TODO sveng 24.05.2023: löschen
//			request.setAttribute("sessionToken", null);
			request.setAttribute("sessionId", null);
			request.setAttribute("permission", 0);
			request.setAttribute("user", null);

			// setup user for anonymous-session:

			connectNewAnonymousUserSessionWithRequest(request);

			return true;

		} else
		{
			return false;
		}
	}

	/**
	 * Authentifiziert eingehende Requests anhand des mitgesendeten
	 * JSON-Web-Tokens.
	 */
	// TODO sveng 25.01.2023: session ist abgelaufen oder besteht gar nicht.
	// TODO sveng 25.01.2023: rückfallseite laden
	// TODO sveng 27.01.2023: session token bei jedem request erneuern?
	public void authenticate(HttpServletRequest request, String jwt)
	{
		UserJDBC authorizedUser = null;

		// TODO sveng 24.05.2023: delete
//		String jwt = (String) request.getAttribute("sessionToken");
		Jws<Claims> jws = null;
		if (jwt != null)
		{
			jws = getValidatedJwsClaimsOpt(request, jwt);
		}
		boolean isJwtValid = jws != null;
		if (jws != null)
		{
			readJWTClaims(request, jws);

		} else
		{
			request.setAttribute("permission", 0);
		}

		boolean allowNewAnonymousSession = false;
		boolean hasBrowserCookies = (boolean) request
		    .getAttribute("hasBrowserCookiesEnabled");
		String sessionId = (String) request.getAttribute("sessionId");
		if (hasBrowserCookies)
		{
			if (sessionId == null)
			{
				allowNewAnonymousSession = true;

			} else if (sessionId.equals("session"))
			{
				allowNewAnonymousSession = true;
			}
		}

		boolean isJwtExpired = true;
		try
		{
			isJwtExpired = (boolean) request.getAttribute("isJwtExpired");

		} catch (Exception e)
		{
			// nix
		}

		if (isJwtValid && !isJwtExpired)
		{
			try
			{
				authorizedUser = userRepository
				    .readBySessionToken(jwt.toString());

				authorizedUser.setSessionToken(sessionId);

				// update JWT
				// TODO sveng 08.02.2023: hier und bei login in eigene methode:
				String newSessionId = UUID.randomUUID().toString();

				// TODO sveng 24.05.2023: löschen
//				String newJwt = createWebtoken(
//				    request, newSessionId, authorizedUser.getName()
//				);
//				authorizedUser.setSessionToken(newJwt);

				authorizedUser.setSessionId(newSessionId);
				// TODO sveng 28.01.2023: try/catch
				userRepository.update(authorizedUser);

				// TODO sveng 24.05.2023: löschen
//				request.setAttribute("sessionToken", newJwt);
				request.setAttribute("sessionId", newSessionId);
				request
				    .setAttribute("permission", authorizedUser.getPermission());
				request.setAttribute("user", authorizedUser);

			} catch (Exception e)
			{
				// setup user for anonymous-session:
				if (allowNewAnonymousSession == true)
				{
					connectNewAnonymousUserSessionWithRequest(request);
				}

				// TODO sveng 29.01.2023: id auf die blacklist setzen. bei
				// vielen
				// versuchen sperren.

				// TODO sveng 29.01.2023: security-service einschalten

				// TODO sveng 29.01.2023: fehlerseite zurückgeben.
			}
		} else
		{
			// setup user for anonymous-session:

			if (allowNewAnonymousSession)
			{
				connectNewAnonymousUserSessionWithRequest(request);
			}

			if (isJwtExpired)
			{
				// TODO sveng 08.02.2023: Login Seite laden.
			}

			// TODO sveng 29.01.2023: id auf die blacklist setzen. bei
			// vielen
			// versuchen sperren.

			// TODO sveng 29.01.2023: security-service einschalten

			// TODO sveng 29.01.2023: fehlerseite zurückgeben.
		}
	}

	private void
	    connectNewAnonymousUserSessionWithRequest(HttpServletRequest request)
	{
		String anonymousUserName = UUID.randomUUID().toString();

		String sessionId = UUID.randomUUID().toString();

		// TODO sveng 24.05.2023: löschen
//		String jwt = createWebtoken(request, sessionId, anonymousUserName);

		UserJDBC anonymousUser = createAnonymousUser(anonymousUserName);

		anonymousUser.setSessionId(sessionId);

		// TODO sveng 24.05.2023: löschen
//		anonymousUser.setSessionToken(jwt);

		// TODO sveng 28.01.2023: try/catch
		long dbId = userRepository.write(anonymousUser);
		if (dbId > -1)
		{
			anonymousUser.setId(dbId);
		}

		// TODO sveng 24.05.2023: löschen
//		request.setAttribute("sessionToken", jwt);
		request.setAttribute("sessionId", sessionId);
		request.setAttribute("permission", anonymousUser.getPermission());
		request.setAttribute("user", anonymousUser);
	}

	private Jws<Claims>
	    getValidatedJwsClaimsOpt(HttpServletRequest request, String jwt)
	{
		Jws<Claims> jws;

		try
		{
			jws = validateWebtoken(jwt);

		} catch (Exception e)
		{
			jws = null;
		}

		return jws;
	}

//	private List<String> handleClaims(HttpServletRequest request, Jws<Claims> jws)
	private void readJWTClaims(HttpServletRequest request, Jws<Claims> jws)
	{
		/*
		 * Header lesen
		 */
		// JwsHeader header = jwt.getHeader();

		/*
		 * Body lesen
		 */
		Claims body = jws.getBody();

		// String issuer = body.get("iss", String.class);

		// Date iatDate = body.get("iat", Date.class);

		Date expTime = body.get("exp", Date.class);
		if (expTime.before(new Date()))
		{
			request.setAttribute("permission", 0);
			request.setAttribute("isJwtExpired", true);
			// TODO sveng 25.01.2023: fehlerseite - link zu neuem login
			// TODO sveng 03.02.2023: fehlermessage
		} else
		{
			request.setAttribute("isJwtExpired", false);
		}

		// cnf Confirmation
//		boolean areCookiesConfirmed = body.get("cnf", Boolean.class);
//		if (areCookiesConfirmed)
//		{
//			request.setAttribute(
//					"hasUserConsentToCookies", areCookiesConfirmed
//			);
//		}
		String sessionId = body.get("sid", String.class);
		request.setAttribute("sessionId", sessionId);

		String originPagePath = body.get("opp", String.class);
		request.setAttribute("originPagePath", originPagePath);
	}

	/**
	 * Erzeugt ein JSON-Web-Token für Client-Side-Sessions.
	 * 
	 * @return
	 */
	public String createWebtoken(HttpServletRequest request, String name)
	{
		Key hmacKey = new SecretKeySpec(
		    Base64.getDecoder().decode(appPropertyService.getHMACSecret()),
		    SignatureAlgorithm.HS256.getJcaName()
		);

		Instant now = Instant.now(); //

		String newPagePath = "";

		String responseType = (String) request.getAttribute("responseType");

		if (responseType.equals("RESPOND") || responseType.equals("FORWARD"))
		{
			newPagePath = (String) request.getAttribute("responsePath");
		}

		/*
		 * list of registered claims:
		 * https://www.iana.org/assignments/jwt/jwt.xhtml#claims
		 */

		String jwt = Jwts.builder() //

		    /*
		     * Header-Caims
		     */
		    .setHeaderParam("typ", "JWT")

		    /*
		     * Payload-Claims
		     * 
		     * Registered Claims: https://www.iana.org/assignments/jwt/jwt.xhtml
		     */

		    // iss (issuer): Issuer of the JWT
		    .setIssuer(appPropertyService.getJwtIssuer())

		    // sub (subject): Subject of the JWT (the user)
		    // .setSubject(appPropertyService.getJwtSubject())

		    // aud (audience): Recipient for which the JWT is intended
		    // .setAudience(appPropertyService.getJwtAudience())

		    // exp (expiration time): Ablaufzeit
		    // TODO sveng 08.02.2023: settings
		    .setExpiration(
		        Date.from(
		            now.plus(
		                appPropertyService.getJwtExperiationInMinutes(),
		                ChronoUnit.MINUTES
		            )
		        )
		    )

		    // iat (issued at time): Ausstellzeit
		    .setIssuedAt(Date.from(now))

			// nbf (not before time): nicht gültig vor Zeit x
			//

			// userName
			// .claim("name", name)

			// email
			// .claim("email", "jane@example.com")

			// jti
//				.setId(UUID.randomUUID().toString())

		    // Session-Id
		    .claim("sid", request.getAttribute("sessionId"))

		    // Origin-Page-Path (Herkunft-Seite des Requests)
		    .claim("opp", newPagePath)

		    /*
		     * Signature
		     */
		    .signWith(hmacKey)

		    /*
		     * Build
		     */
		    .compact(); //

		UserJDBC user = (UserJDBC) request.getAttribute("user");
		user.setSessionToken(jwt);
		userRepository.update(user);

		request.setAttribute("user", user);

		return jwt;
	}

	/**
	 * todo: If the JWT token expires (exp claim value is less than current
	 * system time), the parseClaimsJws() method will throw SignatureException
	 * 
	 * @param jwtString
	 * @return
	 */
	// todo: jwt blacklist vs. whitelist
	private Jws<Claims> validateWebtoken(String jwtString)
	    throws SignatureException
	{
		// Hashed Message Authentication Code
		Key hmacKey = new SecretKeySpec(
		    Base64.getDecoder().decode(appPropertyService.getHMACSecret()),
		    SignatureAlgorithm.HS256.getJcaName()
		);

		Jws<Claims> jws = Jwts.parserBuilder() //
		    .setSigningKey(hmacKey) //
		    .build() //
		    .parseClaimsJws(jwtString);

		return jws;
	}

	private byte[] createSalt() throws NoSuchAlgorithmException
	{
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return salt;
	}

	/**
	 * Erzeugt
	 * 
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	private String generatePasswordHash(String password)
	    throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		int iterations = 1000; // TODO sveng 29.11.2022: ggf. als
		                       // sytemeinstellung, um sicherheit nchträglich
		                       // erhöhen zu können.
		char[] chars = password.toCharArray();
		byte[] salt = createSalt();

		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
		SecretKeyFactory skf = SecretKeyFactory
		    .getInstance("PBKDF2WithHmacSHA1"); // TODO sveng 26.01.2023:
		                                        // security

		byte[] hash = skf.generateSecret(spec).getEncoded();

		return iterations + ":" + toHex(salt) + ":" + toHex(hash);
	}

	private String toHex(byte[] array) throws NoSuchAlgorithmException
	{
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);

		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0)
		{
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else
		{
			return hex;
		}
	}

	private boolean
	    isPasswordValid(String originalPassword, String storedPassword)
	        throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		String[] parts = storedPassword.split(":");
		int iterations = Integer.parseInt(parts[0]);

		byte[] salt = fromHex(parts[1]);
		byte[] hash = fromHex(parts[2]);

		PBEKeySpec spec = new PBEKeySpec(
		    originalPassword.toCharArray(), salt, iterations, hash.length * 8
		);
		SecretKeyFactory skf = SecretKeyFactory
		    .getInstance("PBKDF2WithHmacSHA1");
		byte[] testHash = skf.generateSecret(spec).getEncoded();

		int diff = hash.length ^ testHash.length;
		for (int i = 0; i < hash.length && i < testHash.length; i++)
		{
			diff |= hash[i] ^ testHash[i];
		}
		return diff == 0;
	}

	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
	{
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++)
		{
			bytes[i] = (byte) Integer
			    .parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}

	// TODO sveng 27.01.2023: user in userRepoErzeugen?
	/**
	 * Erzeugt einen User mit den für die Registration erforderlichen Felder.
	 * 
	 * @param name
	 * @param email
	 * @param passwordHash
	 * @return
	 */
	private UserJDBC createUserForRegistration(
	    String name,
	    String email,
	    String passwordHash
	)
	{
		UserJDBC user = new UserJDBC();

		user.setAnonymous(false);
		user.setActive(false);
		user.setPermission(1); // TODO sveng 28.01.2023: PERMISSION welche?

		user.setAccountCreationDate(LocalDateTime.now());
		user.setLastVisitDate(LocalDateTime.now());

		user.setName(name);
		user.setEmail(email);
		user.setPasswordHash(passwordHash);

		user.setActivationKey(RandomStringUtils.randomAlphanumeric(32));
		// TODO sveng 27.01.2023: set experiation time in settings
		LocalDateTime experiationTime = LocalDateTime.now().plusMinutes(60);
		user.setActivationKeyExpirationTime(experiationTime);

		// TODO sveng 28.01.2023: ablauf des account bei nitbenutzung

		return user;
	}

	private UserJDBC createAnonymousUser(String name)
	{
		UserJDBC user = new UserJDBC();

		user.setAnonymous(true);
		user.setActive(true);
		user.setPermission(0);

		user.setAccountCreationDate(LocalDateTime.now());
		user.setLastVisitDate(LocalDateTime.now());

		user.setName(name);

		// TODO sveng 28.01.2023: ablauf des account bei nichtbenutzung

		return user;
	}

	// todo:
	public boolean doRegisterConfirm(boolean notImplemented)
	{
		return true;
	}
}
