const vokabeln = [
["1","¡Hola!","Hallo!","¡Hola!"],
["1","¡Hola!","Guten Morgen!; Guten Tag!","¡Buenos días!"],
["1","¡Hola!","Wie geht´s?","¿Qué tal?"],
["1","¡Hola!","gut","bien"],
["1","¡Hola!","Danke.","Gracias."],
["1","¡Hola!","und","y"],
["1","¡Hola!","Und du?","¿Y tú?"],
["1","¡Hola!","auch","también"],
["1","¡Hola!","Ich bin …","Yo soy…"],
["1","¡Hola!","Ich heiße …","Me llamo…"],
["1","¡Hola!","Wie heißt du?","¿Cómo te llamas?"],
["1","¡Hola!","Wie …?","¿Cómo…?"],
["1","¡Hola!","ja","sí"],
["1","¡Hola!","von; aus","de"],
["1","¡Hola!","Woher …?","¿De dónde… ?"],
["1","¡Hola!","Woher kommst du?","¿De dónde eres?"],
["1","¡Hola!","Deutschland","Alemania"],
["1","¡Hola!","Auf Wiedersehen!; Tschüs!","¡Adiós!"],
["1","¡Hola!","Bis später!","¡Hasta luego!"],
["1","¡Hola!","sehr","muy"],
["1","¡Hola!","So so.; Es geht.","Así así."],
["1","¡Hola!","schlecht","mal"],
["1","¡Hola!","Guten Tag!; Guten Abend!","¡Buenas tardes!"],
["1","¡Hola!","Guten Abend!; Gute Nacht!","¡Buenas noches!"],
["1","¡Hola!","Wie ist dein Nachname?","¿Cómo es tu apellido?"],
["1","¡Hola!","er/sie/es ist","es"],
["1","¡Hola!","der Nachname","el apellido"],
["1","¡Hola!","Man schreibt …","Se escribe…"],
["1","¡Hola!","Wie schreibt man …?","¿Cómo se escribe… ?"],
["1","¡Hola!","mit Akzent","con acento"],
["1","¡Hola!","mit Umlaut","con dos puntos"],
["1","¡Hola!","Doppel-","doble"],
["1","¡Hola!","der Park","el parque"],
["1","¡Hola!","das Hotel","el hotel"],
["1","¡Hola!","das (Stadt) Zentrum","el centro"],
["1","¡Hola!","der Zoo","el zoo"],
["1","¡Hola!","das Restaurant","el restaurante"],
["1","¡Hola!","das Museum","el museo"],
["1","¡Hola!","der Platz","la plaza"],
["1","¡Hola!","die Buchhandlung","la librería"],
["1","¡Hola!","das Haus; die Wohnung","la casa"],
["1","¡Hola!","die Nummer; die Zahl","el número"],
["1","¡Hola!","der Fuchs","el zorro"],
["1","¡Hola!","der Hund","el perro"],
["1","¡Hola!","die Katze","el gato"],
["1","¡Hola!","der Elefant","el elefante"],
["1","¡Hola!","der Bär","el oso"],
["1","¡Hola!","das Pferd","el caballo"],
["1","¡Hola!","der Esel","el burro"],
["1","¡Hola!","die Biene","la abeja"],
["1","¡Hola!","die Vogelspinne; die Tarantel","la tarántula"],
["1","¡Hola!","der Affe","el mono"],
["1","¡Hola!","der Frosch","la rana"],
["1","¡Hola!","die Kuh","la vaca"],
["1","¡Hola!","Ich bin ... Jahre alt.","Tengo... años."],
["1","¡Hola!","mein; meine","mi"],
["1","¡Hola!","das Handy","el móvil"],
["1","¡Hola!","das Telefon","el teléfono"],
["1","¡Hola!","aber","pero"],
["1","¡Hola!","die Stadt","la ciudad"],
["1","¡Hola!","jetzt","ahora"],
["1","¡Hola!","Wie alt bist du?","¿Cuántos años tienes?"],
["2","Mi mundo y yo","die Welt","el mundo"],
["2","Mi mundo y yo","die Schule","el colegio"],
["2","Mi mundo y yo","die Mutter","la madre"],
["2","Mi mundo y yo","der Vater","el padre"],
["2","Mi mundo y yo","die Eltern","los padres (pl.)"],
["2","Mi mundo y yo","der Bruder / die Schwester","el hermano / la hermana"],
["2","Mi mundo y yo","die Geschwister","los hermanos"],
["2","Mi mundo y yo","der Freund / die Freundin","el amigo / la amiga"],
["2","Mi mundo y yo","das Haustier","la mascota"],
["2","Mi mundo y yo","Er / Sie heißt …","Se llama…"],
["2","Mi mundo y yo","dein / deine","tu"],
["2","Mi mundo y yo","besser","mejor"],
["2","Mi mundo y yo","nein; nicht; kein, -e","no"],
["2","Mi mundo y yo","haben","tener (irr.)"],
["2","Mi mundo y yo","Wer …?","¿Quién…? / ¿Quiénes…?"],
["2","Mi mundo y yo","der Lehrer / die Lehrerin","el profesor / la profesora"],
["2","Mi mundo y yo","arbeiten","trabajar"],
["2","Mi mundo y yo","in; auf; an","en"],
["2","Mi mundo y yo","sprechen; reden","hablar"],
["2","Mi mundo y yo","Deutsch, die deutsche Sprache","el alemán"],
["2","Mi mundo y yo","(Ja) , natürlich!","¡Claro!"],
["2","Mi mundo y yo","Spanisch, die spanische Sprache","el español"],
["2","Mi mundo y yo","außerdem","además"],
["2","Mi mundo y yo","lernen","estudiar"],
["2","Mi mundo y yo","Englisch, die englische Sprache","el inglés"],
["2","Mi mundo y yo","zu Hause","en casa"],
["2","Mi mundo y yo","mit","con"],
["2","Mi mundo y yo","immer","siempre"],
["2","Mi mundo y yo","Welche, -r, -s …?; Was für ein, -e ...?; Was ...?","¿Qué… ?"],
["2","Mi mundo y yo","die Sprache","el idioma"],
["2","Mi mundo y yo","etw./jdn hören","escuchar algo/a alguien"],
["2","Mi mundo y yo","antworten","contestar"],
["2","Mi mundo y yo","fragen","preguntar"],
["2","Mi mundo y yo","Schau (mal).","Mira."],
["2","Mi mundo y yo","etw./jdn (an) schauen","mirar algo/a alguien"],
["2","Mi mundo y yo","Das ist…","Este es… / Esta es…"],
["2","Mi mundo y yo","der Junge / das Mädchen","el chico / la chica"],
["2","Mi mundo y yo","(Kommt) Herein!","¡Adelante!"],
["2","Mi mundo y yo","Was ist los?","¿Qué pasa?"],
["2","Mi mundo y yo","das Problem","el problema"],
["2","Mi mundo y yo","etw./jdn suchen","buscar algo/a alguien"],
["2","Mi mundo y yo","das Geheimnis","el secreto"],
["2","Mi mundo y yo","die Idee","la idea"],
["2","Mi mundo y yo","Er / Sie hat keine Ahnung davon.","No tiene ni idea de esto."],
["2","Mi mundo y yo","die Überraschung","la sorpresa"],
["2","Mi mundo y yo","wie","como"],
["2","Mi mundo y yo","der Rucksack","la mochila"],
["2","Mi mundo y yo","nichts","nada"],
["2","Mi mundo y yo","Was …?","¿Qué...?"],
["2","Mi mundo y yo","die Kiste; die Schachtel","la caja"],
["2","Mi mundo y yo","das Monster","el monstruo"],
["2","Mi mundo y yo","der Laden; das Geschäft","la tienda"],
["2","Mi mundo y yo","das Tier","el animal"],
["2","Mi mundo y yo","die Zoohandlung","la tienda de animales"],
["2","Mi mundo y yo","der Papa / die Mama","el papá / la mamá"],
["2","Mi mundo y yo","Keine Widerrede!","¡Nada de peros!"],
["2","Mi mundo y yo","nach; (hin) zu","a"],
["2","Mi mundo y yo","sofort","ahora mismo"],
["2","Mi mundo y yo","sein","ser (irr.)"],
["2","Mi mundo y yo","der Hamster","el hámster"],
["2","Mi mundo y yo","der Fisch","el pez"],
["2","Mi mundo y yo","das Meerschweinchen","el conejillo de Indias"],
["2","Mi mundo y yo","die Schildkröte","la tortuga"],
["2","Mi mundo y yo","der Wellensittich","el periquito"],
["3","El bario","das (Stadt) Viertel","el barrio"],
["3","El bario","die Eisdiele","la heladería"],
["3","El bario","der Supermarkt","el supermercado"],
["3","El bario","das Café; die Cafeteria","la cafetería"],
["3","El bario","die Bibliothek","la biblioteca"],
["3","El bario","das Kino","el cine"],
["3","El bario","das Sportzentrum","el polideportivo"],
["3","El bario","das Bekleidungsgeschäft","la tienda de ropav"],
["3","El bario","der Baum","el árbol"],
["3","El bario","die Apotheke","la farmacia"],
["3","El bario","das Einkaufszentrum","el centro comercial"],
["3","El bario","es gibt; da ist/sind","hay"],
["3","El bario","viel","mucho, -a"],
["3","El bario","wenig","poco, -a"],
["3","El bario","richtig","correcto, -a"],
["3","El bario","groß","grande"],
["3","El bario","modern","moderno, -a"],
["3","El bario","klein","pequeño, -a"],
["3","El bario","schön","bonito, -a"],
["3","El bario","interessant","interesante"],
["3","El bario","Lieblings-","favorito, -a"],
["3","El bario","alt","antiguo, -a"],
["3","El bario","hässlich","feo, -a"],
["3","El bario","langweilig","aburrido, -a"],
["3","El bario","sich befinden; sein","estar (irr.)"],
["3","El bario","die Nachricht","el mensaje"],
["3","El bario","seltsam","raro, -a"],
["3","El bario","Vorsicht!","¡Cuidado!"],
["3","El bario","der Hinweis","la pista"],
["3","El bario","hier","aquí"],
["3","El bario","das Buch","el libro"],
["3","El bario","Los!","¡Vamos!"],
["3","El bario","die Straße","la calle"],
["3","El bario","auf etw./jdn warten","esperar algo/a alguien"],
["3","El bario","die Antwort","la respuesta"],
["3","El bario","etw. schicken","mandar algo"],
["3","El bario","schon","ya"],
["3","El bario","Wo …?","¿Dónde… ?"],
["3","El bario","gegenüber von","enfrente de"],
["3","El bario","die Eintrittskarte","la entrada"],
["3","El bario","für","para"],
["3","El bario","der Film","la película"],
["3","El bario","neben","al lado de"],
["3","El bario","vor","delante de"],
["3","El bario","hinter","detrás de"],
["3","El bario","zwischen","entre"],
["3","El bario","in der Nähe von","cerca de"],
["3","El bario","weit entfernt von","lejos de"],
["3","El bario","das Foto","la foto"],
["3","El bario","der Ort; der Platz","el lugar"],
["3","El bario","weil","porque"],
["3","El bario","der Saft","el zumo"],
["3","El bario","das Obst","la fruta"],
["3","El bario","der Fruchtsaft","el zumo de frutas"],
["3","El bario","lecker","rico, -a"],
["3","El bario","wohnen; leben","vivir"],
["3","El bario","das Wochenende; am Wochenende","el fin de semana"],
["3","El bario","(immer) am Wochenende","los fines de semana"],
["3","El bario","etw. trinken; etw. nehmen","tomar algo"],
["3","El bario","etw. essen","comer algo"],
["3","El bario","das belegte Brötchen","el bocadillo"],
["3","El bario","etw. trinken","beber algo"],
["3","El bario","etwas","algo"],
["3","El bario","vor","antes de"],
["3","El bario","gut","bueno, -a"],
["3","El bario","jdn anrufen","llamar a alguien"],
["3","El bario","das Zimmer","la habitación"],
["3","El bario","die Katastrophe","el desastre"],
["3","El bario","der Moment","el momento"],
["3","El bario","etw. (an) schauen; etw. sehen","ver (irr.) algo"],
["3","El bario","Warum ...?","¿Por qué...?"],
["3","El bario","die Zeit","el tiempo"],
["3","El bario","das Spiel","el juego"],
["3","El bario","der Boden","el suelo"],
["3","El bario","das Glück","la suerte"],
["4","Mi colegio","der Schulhof","el patio"],
["4","Mi colegio","der Computerraum","la sala de Informática"],
["4","Mi colegio","das Sekretariat","la secretaría"],
["4","Mi colegio","die Turnhalle","el gimnasio"],
["4","Mi colegio","der Klassenraum","el aula"],
["4","Mi colegio","der Taschenrechner","la calculadora"],
["4","Mi colegio","der Bleistift","el lápiz"],
["4","Mi colegio","der Ordner","la carpeta"],
["4","Mi colegio","der Radiergummi","la goma"],
["4","Mi colegio","das Heft","el cuaderno"],
["4","Mi colegio","das Federmäppchen","el estuche"],
["4","Mi colegio","der Kugelschreiber","el bolígrafo"],
["4","Mi colegio","der Füller","la pluma"],
["4","Mi colegio","Wie viel ...?","¿Cuánto, -a… ?"],
["4","Mi colegio","heute","hoy"],
["4","Mi colegio","September","septiembre"],
["4","Mi colegio","erste, -r, -s","primero, -a"],
["4","Mi colegio","der Tag","el día"],
["4","Mi colegio","der Unterricht","la clase"],
["4","Mi colegio","etw. glauben; etw. meinen","creer algo"],
["4","Mi colegio","(an) kommen","llegar"],
["4","Mi colegio","die Minute","el minuto"],
["4","Mi colegio","spät; zu spät","tarde"],
["4","Mi colegio","Entschuldigung.","Perdón."],
["4","Mi colegio","neu","nuevo, -a"],
["4","Mi colegio","Mathematik","las Matemáticas"],
["4","Mi colegio","der Klassenlehrer / die Klassenlehrerin","el tutor / la tutora"],
["4","Mi colegio","der Tisch","la mesa"],
["4","Mi colegio","etw. lesen","leer algo"],
["4","Mi colegio","die Liste","la lista"],
["4","Mi colegio","noch einmal; schon wieder","otra vez"],
["4","Mi colegio","etw. brauchen","necesitar algo"],
["4","Mi colegio","die Frage","la pregunta"],
["4","Mi colegio","Hör mal!","¡Oye!"],
["4","Mi colegio","der Herr / die Frau","el señor / la señora"],
["4","Mi colegio","etw./jdm schreiben","escribir algo/a alguien"],
["4","Mi colegio","verärgert","enfadado, -a"],
["4","Mi colegio","sich kaputtlachen","explotar de la risa"],
["4","Mi colegio","die Karotte","la zanahoria"],
["4","Mi colegio","Wie spät ist es?","¿Qué hora es?"],
["4","Mi colegio","morgens","de la mañana"],
["4","Mi colegio","nachmittags; abends","de la tarde"],
["4","Mi colegio","abends; nachts","de la noche"],
["4","Mi colegio","das Unterrichtsfach","la asignatura"],
["4","Mi colegio","Spanisch (als Schulfach, wie das Fach Deutsch in Deutschland)","la Lengua Castellana y Literatura"],
["4","Mi colegio","Geschichte und Erdkunde (Schulfach)","las Ciencias Sociales"],
["4","Mi colegio","Sport (Schulfach)","la Educación Física"],
["4","Mi colegio","Französisch (Schulfach)","el Francés"],
["4","Mi colegio","Naturkunde (Schulfach)","las Ciencias Naturales"],
["4","Mi colegio","Kunst (Schulfach)","la Expresión Plástica"],
["4","Mi colegio","Musik (Schulfach)","la Música"],
["4","Mi colegio","Dir gefällt etw.; Du magst etw. (tun).","Te gusta algo / hacer algo."],
["4","Mi colegio","Mir gefällt etw.; Ich mag etw. (tun)","Me gusta algo / hacer algo."],
["4","Mi colegio","der Stundenplan","el horario"],
["4","Mi colegio","der Montag","el lunes"],
["4","Mi colegio","der Dienstag","el martes"],
["4","Mi colegio","der Mittwoch","el miércoles"],
["4","Mi colegio","der Donnerstag","el jueves"],
["4","Mi colegio","der Freitag","el viernes"],
["4","Mi colegio","der Samstag","el sábado"],
["4","Mi colegio","der Sonntag","el domingo"],
["4","Mi colegio","die Pause","el recreo"],
["4","Mi colegio","von … bis","de… a"],
["4","Mi colegio","um …","a las…"],
["4","Mi colegio","chatten","chatear"],
["4","Mi colegio","über","sobre"],
["4","Mi colegio","Wie war …?","¿Qué tal… ?"],
["4","Mi colegio","euer, eure","vuestro, -a"],
["4","Mi colegio","dieses Jahr","este año"],
["4","Mi colegio","unser, unsere","nuestro, -a"],
["4","Mi colegio","streng","estricto, -a"],
["4","Mi colegio","die Hausaufgaben","los deberes (pl.)"],
["4","Mi colegio","die Klasse","la clase"],
["4","Mi colegio","das Gebäude; das Wohnhaus","el edificio"],
["4","Mi colegio","sich verabreden; sich treffen","quedar"],
["4","Mi colegio","lustig","divertido, -a"],
["4","Mi colegio","die Geschichte","la historia"],
["4","Mi colegio","bis","hasta"],
["4","Mi colegio","morgen","mañana"],
["4","Mi colegio","Wie langweilig!","¡Qué rollo!"],
["4","Mi colegio","auch nicht","tampoco"],
["4","Mi colegio","die Klassenarbeit","el examen"],
["4","Mi colegio","nur","solo"],
["4","Mi colegio","zeichnen","dibujar"],
["4","Mi colegio","die Musik","la música"],
["4","Mi colegio","sehr; viel","mucho"],
["4","Mi colegio","erlauben","permitir"],
["4","Mi colegio","Ihm / Ihr gefällt etw.; Er/Sie mag etwas (tun).","Le gusta algo / hacer algo."],
["5","Mi habitaciòn","das Bett","la cama"],
["5","Mi habitaciòn","der Schreibtisch","el escritorio"],
["5","Mi habitaciòn","der Schrank","el armario"],
["5","Mi habitaciòn","das Regal","la estantería"],
["5","Mi habitaciòn","der Stuhl","la silla"],
["5","Mi habitaciòn","die Gitarre","la guitarra"],
["5","Mi habitaciòn","der Fernseher","el televisor"],
["5","Mi habitaciòn","der Computer","el ordenador"],
["5","Mi habitaciòn","das Poster","el póster"],
["5","Mi habitaciòn","das Fenster","la ventana"],
["5","Mi habitaciòn","die Lampe","la lámpara"],
["5","Mi habitaciòn","die Tür","la puerta"],
["5","Mi habitaciòn","die Wand","la pared"],
["5","Mi habitaciòn","die CD","el CD"],
["5","Mi habitaciòn","auf","encima de"],
["5","Mi habitaciòn","unter","debajo de"],
["5","Mi habitaciòn","links von","a la izquierda de"],
["5","Mi habitaciòn","rechts von","a la derecha de"],
["5","Mi habitaciòn","der Deutsche / die Deutsche","el alemán / la alemana"],
["5","Mi habitaciòn","etw. aufräumen","ordenar algo"],
["5","Mi habitaciòn","Das stimmt doch, oder?; … nicht wahr?","¿Verdad?"],
["5","Mi habitaciòn","etw. tun können; etw. tun dürfen","poder (-ue-) hacer algo"],
["5","Mi habitaciòn","etw. anfassen","tocar algo"],
["5","Mi habitaciòn","die Sache; das Ding","la cosa"],
["5","Mi habitaciòn","sehr geehrte, -r","estimado, -a"],
["5","Mi habitaciòn","der Brief","la carta"],
["5","Mi habitaciòn","etw. machen","hacer (irr.) algo"],
["5","Mi habitaciòn","etw. tun müssen","tener que hacer algo"],
["5","Mi habitaciòn","der Schüler / die Schülerin","el alumno / la alumna"],
["5","Mi habitaciòn","der Gruß","el saludo"],
["5","Mi habitaciòn","Das darf doch wohl nicht wahr sein.","Esto no puede ser."],
["5","Mi habitaciòn","Wie entsetzlich!","¡Qué horror!"],
["5","Mi habitaciòn","auf etw. Lust haben; Lust haben etw. zu tun","tener ganas de algo / de hacer algo"],
["5","Mi habitaciòn","dann","entonces"],
["5","Mi habitaciòn","so; auf diese Weise","así"],
["5","Mi habitaciòn","die E-Mail","el correo (electrónico)"],
["5","Mi habitaciòn","etw. wollen","querer (-ie-) algo"],
["5","Mi habitaciòn","in Ruhe lassen","dejar tranquilo, -a"],
["5","Mi habitaciòn","ruhig","tranquilo, -a"],
["5","Mi habitaciòn","letzte, -r, -s","último, -a"],
["5","Mi habitaciòn","zum letzten Mal","por última vez"],
["5","Mi habitaciòn","etw. (hinein) legen; etw. setzen; etw. stellen","poner (-g-) algo"],
["5","Mi habitaciòn","Es tut mir leid.","Lo siento."],
["5","Mi habitaciòn","die Fernsehserie","la serie"],
["5","Mi habitaciòn","das Fernsehen","la tele(visión)"],
["5","Mi habitaciòn","dann; danach","después"],
["5","Mi habitaciòn","Jetzt reicht’s )mir( aber!","¡Basta ya!"],
["5","Mi habitaciòn","Hausarrest haben","estar castigado, -a sin salir"],
["5","Mi habitaciòn","die Freizeit","el tiempo libre"],
["5","Mi habitaciòn","gehen; fahren","ir (irr.)"],
["5","Mi habitaciòn","shoppen gehen","ir de compras"],
["5","Mi habitaciòn","spielen (Musikinstrument","tocar"],
["5","Mi habitaciòn","Rad fahren","montar en bicicleta"],
["5","Mi habitaciòn","das Fahrrad","la bicicleta"],
["5","Mi habitaciòn","mit jdm etw. spielen","jugar (-ue-) a algo con alguien"],
["5","Mi habitaciòn","der Fußball","el fútbol"],
["5","Mi habitaciòn","telefonieren","hablar por teléfono"],
["5","Mi habitaciòn","der Sport","el deporte"],
["5","Mi habitaciòn","Mir ja.; Ich ja.","A mí sí."],
["5","Mi habitaciòn","Mir nicht.; Ich nicht.","A mí no."],
["5","Mi habitaciòn","Mir auch.","A mí también."],
["5","Mi habitaciòn","Mir auch nicht.; Ich auch nicht.","A mí tampoco."],
["5","Mi habitaciòn","(am) Morgen; (am) Vormittag","por la mañana"],
["5","Mi habitaciòn","ausgehen","salir (-g-)"],
["5","Mi habitaciòn","(am) Nachmittag","por la tarde"],
["5","Mi habitaciòn","das Training","el entrenamiento"],
["5","Mi habitaciòn","das Fußballspiel","el partido de fútbol"],
["5","Mi habitaciòn","die Party; das Fest","la fiesta"],
["5","Mi habitaciòn","Wie …!","¡Qué… !"],
["5","Mi habitaciòn","toll; klasse","guay"],
["5","Mi habitaciòn","genial","genial"],
["5","Mi habitaciòn","etw. kaufen","comprar algo"],
["5","Mi habitaciòn","die Pizza","la pizza"],
["5","Mi habitaciòn","zuerst","primero"],
["5","Mi habitaciòn","sicher; sicherlich","seguramente"],
["5","Mi habitaciòn","die Hilfe","la ayuda"],
["5","Mi habitaciòn","schnell","rápidamente"],
["5","Mi habitaciòn","dumm","tonto, -a"],
["5","Mi habitaciòn","Einverstanden.; O.K.","Vale."],
["5","Mi habitaciòn","perfekt","perfecto, -a"],
["6","El cumpleaños de Maite","Wann …?","¿Cuándo…?"],
["6","El cumpleaños de Maite","der Geburtstag","el cumpleaños"],
["6","El cumpleaños de Maite","Januar","enero"],
["6","El cumpleaños de Maite","Februar","febrero"],
["6","El cumpleaños de Maite","März","marzo"],
["6","El cumpleaños de Maite","April","abril"],
["6","El cumpleaños de Maite","Mai","mayo"],
["6","El cumpleaños de Maite","Juni","junio"],
["6","El cumpleaños de Maite","Juli","julio"],
["6","El cumpleaños de Maite","August","agosto"],
["6","El cumpleaños de Maite","September","septiembre"],
["6","El cumpleaños de Maite","Oktober","octubre"],
["6","El cumpleaños de Maite","November","noviembre"],
["6","El cumpleaños de Maite","Dezember","diciembre"],
["6","El cumpleaños de Maite","das Geschenk","el regalo"],
["6","El cumpleaños de Maite","anfangen; beginnen","empezar (-ie-)"],
["6","El cumpleaños de Maite","der Frühling","la primavera"],
["6","El cumpleaños de Maite","die Ferien; der Urlaub","las vacaciones"],
["6","El cumpleaños de Maite","der Sommer","el verano"],
["6","El cumpleaños de Maite","der Strand","la playa"],
["6","El cumpleaños de Maite","Weihnachten","la Navidad"],
["6","El cumpleaños de Maite","der Winter","el invierno"],
["6","El cumpleaños de Maite","der Herbst","el otoño"],
["6","El cumpleaños de Maite","etw. feiern","celebrar algo"],
["6","El cumpleaños de Maite","dort","allí"],
["6","El cumpleaños de Maite","wichtig","importante"],
["6","El cumpleaños de Maite","Bitte.","Por favor."],
["6","El cumpleaños de Maite","um … zu","para (+ inf.)"],
["6","El cumpleaños de Maite","etw. organisieren","organizar algo"],
["6","El cumpleaños de Maite","der Kuss","el beso"],
["6","El cumpleaños de Maite","jdn einladen","invitar a alguien"],
["6","El cumpleaños de Maite","die Aufgabe","la tarea"],
["6","El cumpleaños de Maite","einfach; leicht","fácil"],
["6","El cumpleaños de Maite","etw./jdn besuchen","visitar algo/a alguien"],
["6","El cumpleaños de Maite","der Streber, die Streberin","el empollón, la empollona"],
["6","El cumpleaños de Maite","der Großvater, die Großmutter","el abuelo, la abuela"],
["6","El cumpleaños de Maite","die Großeltern","los abuelos (pl.)"],
["6","El cumpleaños de Maite","allein, -e","solo, -a"],
["6","El cumpleaños de Maite","vielleicht","a lo mejor"],
["6","El cumpleaños de Maite","kommen","venir (irr.)"],
["6","El cumpleaños de Maite","die Information","la información"],
["6","El cumpleaños de Maite","jdn überraschen","sorprender a alguien"],
["6","El cumpleaños de Maite","die Themenparty","la fiesta temática"],
["6","El cumpleaños de Maite","oder","o"],
["6","El cumpleaños de Maite","notwendig","necesario, -a"],
["6","El cumpleaños de Maite","die Einladung","la invitación"],
["6","El cumpleaños de Maite","das Essen","la comida"],
["6","El cumpleaños de Maite","das Getränk","la bebida"],
["6","El cumpleaños de Maite","die Torte; der Kuchen","la tarta"],
["6","El cumpleaños de Maite","etw. dekorieren; etw. schmücken","decorar algo"],
["6","El cumpleaños de Maite","etw. zubereiten; etw. vorbereiten","preparar algo"],
["6","El cumpleaños de Maite","der Kartoffelchip","la patata frita"],
["6","El cumpleaños de Maite","das Wasser","el agua (f.)"],
["6","El cumpleaños de Maite","etw./jdn (mit) bringen","traer (irr.) algo/a alguien"],
["6","El cumpleaños de Maite","das Lied","la canción"],
["6","El cumpleaños de Maite","der Plan","el plan"],
["6","El cumpleaños de Maite","typisch","típico, -a"],
["6","El cumpleaños de Maite","die Schokolade","el chocolate"],
["6","El cumpleaños de Maite","jeder / jede","cada persona"],
["6","El cumpleaños de Maite","jdn veräppeln","tomar el pelo a alguien"],
["6","El cumpleaños de Maite","die Packung","el paquete"],
["6","El cumpleaños de Maite","der Keks","la galleta"],
["6","El cumpleaños de Maite","der Käse","el queso"],
["6","El cumpleaños de Maite","die Tüte","la bolsa"],
["6","El cumpleaños de Maite","die Flasche","la botella"],
["6","El cumpleaños de Maite","das Erfrischungsgetränk","el refresco"],
["6","El cumpleaños de Maite","die Orange","la naranja"],
["6","El cumpleaños de Maite","das Baguette","la barra de pan"],
["6","El cumpleaños de Maite","das Brot","el pan"],
["6","El cumpleaños de Maite","kosten","costar (-ue-)"],
["6","El cumpleaños de Maite","der Euro","el euro"],
["6","El cumpleaños de Maite","der Cent","el céntimo"],
["7","La ropa","der Rock","la falda"],
["7","La ropa","die Bluse","la blusa"],
["7","La ropa","das T-Shirt","la camiseta"],
["7","La ropa","die Jacke","la chaqueta"],
["7","La ropa","die Socke","el calcetín"],
["7","La ropa","der Schuh","el zapato"],
["7","La ropa","die Sportschuhe","las zapatillas de deporte (pl.)"],
["7","La ropa","die Jeans(hose)","los vaqueros (pl.)"],
["7","La ropa","der Mantel","el abrigo"],
["7","La ropa","die Hose","los pantalones"],
["7","La ropa","der Schal","la bufanda"],
["7","La ropa","der Handschuh","el guante"],
["7","La ropa","der Pullover","el jersey"],
["7","La ropa","die Brille","las gafas (pl.)"],
["7","La ropa","die Sonnenbrille","las gafas de sol (pl.)"],
["7","La ropa","der Badeanzug; die Badehose","el bañador"],
["7","La ropa","das Kleid","el vestido"],
["7","La ropa","die Sandale","la sandalia"],
["7","La ropa","der Koffer","la maleta"],
["7","La ropa","Ich glaub, ich spinne!","¡No me lo puedo creer!"],
["7","La ropa","verbringen","pasar"],
["7","La ropa","die Küste","la costa"],
["7","La ropa","fast","casi"],
["7","La ropa","alle; jede, -r, -s","todos, -as"],
["7","La ropa","saber (irr.) algo","etw. wissen"],
["7","La ropa","gracioso, -a","witzig"],
["7","La ropa","verde","grün"],
["7","La ropa","pues","also"],
["7","La ropa","dieser, diese, dieses (hier)","este, -a, -os, -as"],
["7","La ropa","dieser, diese, dieses (da)","ese, -a, -os, -as"],
["7","La ropa","etw. lieber mögen","preferir (-ie-) algo"],
["7","La ropa","rosa","rosa"],
["7","La ropa","der Bikini","el bikini"],
["7","La ropa","rot","rojo, -a"],
["7","La ropa","Wie findest du …?","¿Qué te parece)n(…?"],
["7","La ropa","Ich finde …","Me parece)n( …"],
["7","La ropa","da","ahí"],
["7","La ropa","blau","azul"],
["7","La ropa","dunkel","oscuro, -a"],
["7","La ropa","dir steht / dir stehen","te queda(n)"],
["7","La ropa","Sie beachten uns überhaupt nicht.","Pasan completamente de nosotras."],
["7","La ropa","eitel","presumido, -a"],
["7","La ropa","mir steht / mir stehen","me queda(n)"],
["7","La ropa","die Größe","la talla"],
["7","La ropa","breit; weit","ancho, -a"],
["7","La ropa","kurz","corto, -a"],
["7","La ropa","zu + Adj.","demasiado"],
["7","La ropa","lang","largo, -a"],
["7","La ropa","eng","estrecho, -a"],
["7","La ropa","jener, jene, jenes","aquel, aquella, aquellos, aquellas"],
["7","La ropa","hell","claro, -a"],
["7","La ropa","omamäßig","de abuela"],
["7","La ropa","das (Speise) Eis","el helado"],
["7","La ropa","der/die/das","que"],
["7","La ropa","nächstes Mal","la próxima vez"],
["7","La ropa","etw. tragen","llevar algo"],
["7","La ropa","gelb","amarillo, -a"],
["7","La ropa","orange(farbe)","naranja"],
["7","La ropa","schwarz","negro, -a"],
["7","La ropa","weiß","blanco, -a"],
["7","La ropa","grau","gris"],
["7","La ropa","braun","marrón"],
["7","La ropa","lila","lila"],
];

const allLessons = extractUniqueLessons();
function extractUniqueLessons() {
	const al = new Set();
	let c = 1
	for (const vokabel of vokabeln) {
		if(c==parseInt(vokabel[0])){
			const l = {
                number: vokabel[0],
                name: vokabel[1]
            };
			al.add(l);
			c++;
		}
		/* al.add(vokabel[0]); */
	}
	return Array.from(al);
}

if (document.body.id === "index"){
	
	// Elemente
	const deutscheVokabelDiv = document.getElementById("deutscheVokabel");
	const spanischeVokabelDiv = document.getElementById("spanischeVokabel");
	const btnLesson = document.getElementById("btn-question");
	const btnAnswer = document.getElementById("btn-answer");
	const btnPlay = document.getElementById("btn-play");
	const btnNext = document.getElementById("btn-next");
	const btnAutoPlay = document.getElementById("btn-auto-play");

	// Einstellungen
	const randomSelectedVokabeln = [];
	const randomAvailableVokabeln = [];
	let randomMode = localStorage.getItem('randomMode') === 'true' ?? false;
	let audioMode = localStorage.getItem('audioMode') === 'true' ?? false;
	let selectedLesson = JSON.parse(localStorage.getItem("selectedLesson")) ?? allLessons[0];
	const { startIndex, endIndex } = getStartEndIndexForLesson(selectedLesson.number);
	initRandomAvaillableVokabeln();
	let currentVokabelIndex;
	loadNextVokabel();
	let playState = false;
	let delay = localStorage.getItem("delay") ?? '1000';

	// GUI initialization
	document.getElementById("lesson-heading").innerHTML = selectedLesson.name;

	if(!audioMode){
		btnLesson.addEventListener("click", function() {
			deutscheVokabelDiv.style.display = "block";
			spanischeVokabelDiv.style.display = "none";
		});
		btnAnswer.addEventListener("click", function() {
			deutscheVokabelDiv.style.display = "none";
			spanischeVokabelDiv.style.display = "block";
		});
		btnPlay.addEventListener("click", function() {
			responsiveVoice.speak(vokabeln[currentVokabelIndex][3], "Spanish Female");
		});
		btnNext.addEventListener("click", function() {
			loadNextVokabel()
		});
	}
	
	if(audioMode){
		btnLesson.style.display = "none";
		btnAnswer.style.display = "none";
		btnPlay.style.display = "none";
		btnNext.style.display = "none";
		btnAutoPlay.style.display = "block";
		btnAutoPlay.className = "btn btn-primary btn-lg w-100 mt-3";
		btnAutoPlay.addEventListener("click", function() {
			setPlayState();
		});
	}
	
	function setPlayState(){
		if(playState){
			playState = false;
			btnAutoPlay.innerHTML = "Play";
		}else{
			playState = true;
			btnAutoPlay.innerHTML = "Pause";
			speakGermanThenSpanish();
		}
	}
	
	function speakGermanThenSpanish() {
		if (!playState) {
			return;
		}
		const germanText = vokabeln[currentVokabelIndex][2];
		const spanishText = vokabeln[currentVokabelIndex][3];

		responsiveVoice.speak(germanText, "Deutsch Female", {
			onend: function() {
				if (!playState) {
					return;
				}
				// Deutsch wurde gesprochen, jetzt eine Pause
				setTimeout(function() {
					if (!playState) {
						return;
					}
					responsiveVoice.speak(spanishText, "Spanish Female", {
						onend: function() {
							if (!playState) {
								return;
							}
							// Spanisch wurde gesprochen, jetzt eine Pause
							setTimeout(function() {
								if (!playState) {
									return;
								}
								loadNextVokabel();
								speakGermanThenSpanish(); // Von vorne beginnen
							}, 1000);
						},
					});
				}, delay);
			},
		});
	}

	function loadNextVokabel() {
		if (randomMode) {
			if (randomAvailableVokabeln.length === 0) {
				initRandomAvaillableVokabeln();
			}
			const randomIndex = Math.floor(Math.random() * randomAvailableVokabeln.length);
			const selectedIndex = randomAvailableVokabeln[randomIndex];
			randomAvailableVokabeln.splice(randomIndex, 1);
			randomSelectedVokabeln.push(selectedIndex);
			currentVokabelIndex = selectedIndex;
		} else {
			if(currentVokabelIndex === null || currentVokabelIndex === undefined){
				currentVokabelIndex = startIndex;
			}else{
				currentVokabelIndex++;
				if (currentVokabelIndex > endIndex) {
					currentVokabelIndex = startIndex;
				}
			}
		}
		updateVokabelAnzeige();
	}
	
	function initRandomAvaillableVokabeln(){
		if(randomMode){
			randomAvailableVokabeln.length=0;
			for (let i = startIndex; i <= endIndex; i++) {
				randomAvailableVokabeln.push(i);
			}
		}
	}
	
	function updateVokabelAnzeige() {
		const [id,name,deutscheVokabel,spanischeVokabel] = vokabeln[currentVokabelIndex];
		deutscheVokabelDiv.textContent = deutscheVokabel;
		spanischeVokabelDiv.textContent = spanischeVokabel;
		deutscheVokabelDiv.style.display = "block";
		spanischeVokabelDiv.style.display = "none";
	}
	
	function getStartEndIndexForLesson(lessonNumber) {
		let startIndex = -1;
		let endIndex = -1;
		for (let i = 0; i < vokabeln.length; i++) {
		const currentLessonNumber = vokabeln[i][0];
			if (currentLessonNumber === lessonNumber) {
				if (startIndex === -1) {
				// Wenn `startIndex` noch nicht festgelegt ist, setzen Sie ihn auf den aktuellen Index
				startIndex = i;
				}
				// Aktualisieren Sie `endIndex` auf den aktuellen Index, während Sie weiterhin nach übereinstimmenden Lektionsnummern suchen
				endIndex = i;
			}
		}
		return {
			startIndex: startIndex,
			endIndex: endIndex,
		};
	}
	
}else if (document.body.id === "lessons"){
	
		let selectedLessonString = localStorage.getItem("selectedLesson");
		let selectedLesson = JSON.parse(selectedLessonString);
		if (!selectedLesson) {
			selectedLesson = allLessons[0];
		}
		
		const buttonsContainer = document.getElementById("buttons-container");

		for (const lesson of allLessons) {
			const lessonContainer = document.createElement("div");
			lessonContainer.className = "row mb-2";
			const button = document.createElement("button");
			button.className = "btn btn-outline-secondary btn-lg text-start col-lg-6 col-md-8 col-sm-12";
			button.type = "button";
			button.textContent = lesson.name;

			// Überprüfen Sie, ob diese Lektion die zuvor ausgewählte Lektion ist
			if (selectedLesson.number === lesson.number) {
				button.classList.add("active"); // Setzen Sie den Toggle-Zustand aktiv
			}

			button.addEventListener("click", function () {
				
				// Überprüfen, ob bereits ein Button ausgewählt ist
				const activeButton = buttonsContainer.querySelector(".btn.active");

				// Vorherigen Button deaktivieren
				if (activeButton) {
					activeButton.classList.remove("active");
				}

				// Aktuellen Button aktivieren
				this.classList.add("active");
				
				// Ausgewählte Lektion aktualisieren
				selectedLesson = lesson;

				// Auswahl im Local Storage speichern
				localStorage.setItem("selectedLesson", JSON.stringify(lesson));
			});

			lessonContainer.appendChild(button);
			buttonsContainer.appendChild(lessonContainer);
		}
		
}else if (document.body.id === "settings"){
	
		initToggleButtonsFromLocalStorage('btn-random', 'randomMode');
		initToggleButtonsFromLocalStorage('btn-audio', 'audioMode');
		initDelayFromLocalStorage();

		function initToggleButtonsFromLocalStorage(checkboxId, key) {
			const checkbox = document.getElementById(checkboxId);
			const toggleStateString = localStorage.getItem(key);

			if (toggleStateString !== null) {
				let toggleState = JSON.parse(toggleStateString);
				if (toggleState) {
					checkbox.checked = true;
				} else {
					checkbox.checked = false;
				}
			} else {
				checkbox.checked = false;
				saveBtnStateToLocalStorage(checkboxId, key);
			}
		}
		
		function initDelayFromLocalStorage(){
			const input = document.getElementById('delay-input');
			const delay = localStorage.getItem('delay');
			if(delay){
				input.value = delay;
			}else{
				input.value = 1000;
				localStorage.setItem('delay','1000');
			}
		}
		
		document.getElementById('btn-random').addEventListener('change', function () {
			saveBtnStateToLocalStorage('btn-random', 'randomMode');
		});

		document.getElementById('btn-audio').addEventListener('change', function () {
			saveBtnStateToLocalStorage('btn-audio', 'audioMode');
		});
		
		function saveBtnStateToLocalStorage(checkboxId, key) {
			const checkbox = document.getElementById(checkboxId);
			localStorage.setItem(key, JSON.stringify(checkbox.checked));
		}	
		
		document.getElementById('delay-input').addEventListener('input', function () {
			localStorage.setItem('delay', event.target.value);
		});
		
		document.getElementById('delay-input').addEventListener("click", function () {
			this.select();
		});
}
