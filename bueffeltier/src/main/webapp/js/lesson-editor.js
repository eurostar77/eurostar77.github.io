/**
 * 
 */
document.addEventListener('DOMContentLoaded', showPageModal("This is an example message.","info"));
/*******************************************************************************
 * Website - Init seitenspezifisch
 ******************************************************************************/
if (window.location.pathname === '/bueffeltier/lesson-editor') {
	var inputName = "";
	var inputTheme = "";
	var btnSave = "";	
	var cards = document.getElementById('cards');
	var modelCard = document.getElementById('card1');
	var addCardBtns = document.querySelectorAll('.addCardBtn');
	addCardBtns.forEach(function(button) {
		button.addEventListener('click', fnAddCards);
	});
	// addCardBtns.addEventListener('click', function() {
	// 	// Event-Handler für Button 1 auf Seite 1
	// });
} else if (window.location.pathname === '/') {
	var testButton = document.querySelector(".testButton");
	testButton.addEventListener("click", function() {
		sendAjaxPost(
			"http://localhost:8080/bueffeltier/api", 
			jsonData,
			"CREATE_FLASHCARDS",
			successMsg, 
			errorMsg
			);
	});
}

/*******************************************************************************
 * Website - ElementCloneCopy
 ******************************************************************************/



/*******************************************************************************
 * Website - ElementIncrementalCopy
 ******************************************************************************/
function copyAndIncrementElements(limit) {
	
	const elements = document.getElementsByClassName("incrementable");

	if (elements.length === 0) {
		showPageModal();
		console.warn("Es wurden keine Elemente mit der Klasse 'incrementable' gefunden.");
		return;
	}

	// Überprüfen, ob die Anzahl der Elemente das Limit überschreitet
	if (elements.length >= limit) {
		
    	console.error("Die maximale Anzahl an Elementen wurde erreicht.");
    	return;
	}

	// Funktion zum Inkrementieren der ID
	function incrementId(id) {
    	const regex = /(\D+)?(\d+)/; // Muster zum Erkennen von Buchstaben und Zahlen im ID-Index
    	const matches = id.match(regex);
		const prefix = matches[1] || ""; // Vorhandener Buchstabenpräfix (falls vorhanden)
    	const index = parseInt(matches[2]); // Vorhandener Index
    	const newIndex = index + 1; // Neuer Index
    	return prefix + newIndex.toString().padStart(index.toString().length, "0"); // Neue ID generieren
	}

	// Funktion zum Kopieren und Aktualisieren eines Elements
	function copyAndIncrementElement(element) {
	const clone = element.cloneNode(true); // Element kopieren (einschließlich Kindern)
	const idsToUpdate = clone.querySelectorAll("[id]"); // Alle IDs im geklonten Element auswählen

	// IDs in den geklonten Elementen aktualisieren
	idsToUpdate.forEach((el) => {
		const oldId = el.id;
		const newId = incrementId(oldId);
		el.id = newId;
	});

	// Geklontes Element in den DOM einfügen
	element.parentNode.insertBefore(clone, element.nextSibling);
	}

  // Prüfen, ob Elemente gefunden wurden
  if (elements.length > 0) {
    // Alle Elemente kopieren und aktualisieren
    const elementsToCopy = Array.from(elements);
    elementsToCopy.forEach(copyAndIncrementElement);
  } else {
    console.warn("Es wurden keine Elemente mit der Klasse 'incrementable' gefunden.");
  }
}
/*******************************************************************************
 * Website - Buttonsave
 ******************************************************************************/




/*******************************************************************************
 * Website - Autosave
 ******************************************************************************/

	document.addEventListener("DOMContentLoaded", () => {
	
		const SAVING_MESSAGE = "Saving...";
		const SAVED_MESSAGE = "All changes saved.";
	
		document
	    .querySelectorAll(".autosave-message")
	    .forEach((el) => (el.textContent = SAVED_MESSAGE));
	
		document.querySelectorAll(".autosave").forEach((inputField) => {
			inputField.addEventListener("change", async () => {
			const name = inputField.getAttribute("name");
			const value = inputField.value;
			const url = inputField.closest(".container").dataset.autosaveUrl;
			const actionName = inputField.closest(".container").dataset.action;
			const autosaveMessageEl = inputField
	        .closest(".container")
	        .querySelector(".autosave-message");
			const formData = new FormData();
	
			formData.append(name, value);
			// formData.append("action", action);
			autosaveMessageEl.classList.add("autosave-message--saving");
			autosaveMessageEl.textContent = SAVING_MESSAGE;
	
		// 	const response = await fetch(url, {
		// 		method: "POST",
		// 		body: formData
	    //   });
	
		  	sendAjaxPost(url, formData, actionName, successCallback, errorCallback);
	
				autosaveMessageEl.classList.remove("autosave-message--saving");
				autosaveMessageEl.textContent = SAVED_MESSAGE;
			});
		});
	});
/*******************************************************************************
 * Website - Modaler Dialog
 ******************************************************************************/

// todo: Modal optional nicht über das JS im Script-Tag öffnen.
//function showPageModal(){
//	var modal = document.getElementById('pageModal');
//	var modalInstance = new bootstrap.Modal(modal);
//	modalInstance.show();
//}

function showPageModal(message, messageType) {
	
	var modal = document.getElementById('pageModal');
	var modalInstance = new bootstrap.Modal(modal);
	var modalTitle = document.getElementById('pageModal-title');
	var modalBody = document.getElementById('modal-body');
	
	if (modal && modalTitle && modalBody) {
		var titleMessage = modalTitle.innerHTML = messageType === 'warning' ? 'Warning' : 'Info';
		var icon = messageType === 'warning' ? '<i class="bi bi-exclamation-triangle"></i>' : '<i class="bi bi-info-circle"></i>';
		modalTitle.innerHTML = icon + ' ' + titleMessage;
		modalBody.innerHTML = message;
		modalInstance.show();
	}
}
    
/*******************************************************************************
 * Website - Send AJAX POST
 ******************************************************************************/
function sendAjaxPost(url, formData, actionName, successCallback, errorCallback) {
		// const formData = new FormData();
		// formData.append('json', JSON.stringify(dataObject));
		formData.append('action', actionName);
		
	//	dataObject.action = actionName;
	//	dataObject.ajax = "ajax";
		fetch(url, {
			method: 'POST',
			headers: {
	//			'Content-Type': 'application/json',
				'X-Requested-With': 'XMLHttpRequest'
			},
	//    	body: JSON.stringify(dataObject)
			body: formData
		})
		.then(response => {
			if (response.ok) {
				return response.json();
			} else {
				throw new Error('HTTP status ' + response.status);
			}
		})
		.then(responseData => {
			if (successCallback) {
				successCallback(responseData);
			}
		})
		.catch(error => {
			if (errorCallback) {
			errorCallback(error);
			}
		});
	}
	
	function successMsg(response) {
		alert(response);
	}
	
	function errorMsg(error) {
		alert(error);
}

/*******************************************************************************
* Check Local Storage
******************************************************************************/
// if (global.localStorage) {
//    global.localStorage.setItem('layout', JSON.stringify({
//      [key]: value
//    }));
//  }

/*******************************************************************************
 * Seite - "/""
 ******************************************************************************/
var jsonData = { name: 'John', age: 30 };

//const dataObject = {
//  name: "John Doe",
//  age: 30,
//  address: {
//    street: "123 Main St",
//    city: "Example City"
//  },
//  phoneNumbers: [
//    "123456789",
//    "987654321"
//  ]
//};


 /*******************************************************************************
 * Init
 ******************************************************************************/
// testButton.addEventListener("click", function() {
//	alert("Button wurde geklickt!");
//});
 
 
 
 
/*******************************************************************************
 * Einzelne Karte
 ******************************************************************************/
 var btnDelCard = "";
 var btnMoveUp = "";
 var btnMoveDown = "";
 
 
/*******************************************************************************
* Save LearningTask
******************************************************************************/


function saveLearningTask(){
	
} 
 
 
 
 
/*******************************************************************************
* "/bueffeltier/lesson-editor" - Add Card
*******************************************************************************/
function fnAddCards(){
	if (addCardBtns!==null)
	{
		addCardBtns.forEach(function(button) {
		
			button.addEventListener('click', function()
			{
				// New Card:
				var clonedCard = modelCard.cloneNode(true);
				
				// Last Card in Cards:
				var lastCard = cards.lastElementChild;
			//                alert('lastId ' + lastCard.id);
							
				// Cloned Card set Id:
				var lastCardId = lastCard.id;
				var newIdNumber;
				
				var matches = lastCardId.match(/(\d+)/);
				if (matches)
				{
					newIdNumber = parseInt(matches[0])+1;
				}
				
				clonedCard.id = 'card' + newIdNumber;
				
				
				// New Header Text:
			//                var newHeaderId = 'ch' + newIdNumber;
			//                clonedCard.getElementById('ch1').id = 'ch2' + newIdNumber;
				clonedCard.querySelector('h2 i').innerHTML = 'Aufgabe ' + newIdNumber;
				clonedCard.querySelector('h2 i').id = 'ch' + newIdNumber;
			//                clonedCard.getElementsByTagName("h3").innerHTML = "your new header";
			
				// New Card set Text:
				var all = clonedCard.querySelectorAll('textarea');
				all[0].id = 'q' + newIdNumber;
				all[0].name = 'qn' + newIdNumber;
				all[0].innerHTML = '';
				all[1].id = 'a' + newIdNumber;
				all[1].name = 'an' + newIdNumber;
				all[1].innerHTML = '';
							
							
			//                for(var i = 0; i < all.length; i++){
			//                    all[i].id = 'a' + newIdNumber;
			//                }
							
			//                document.getElementById('a2').id = 'ax';
			//                document.getElementById('l' + lastCardId).id = 'l' + lastCardId + 1;
			//                document.getElementById('a' + lastCardId + 1).innerHTML = "";
			//                document.getElementById('l' + lastCardId + 1).innerHTML = "";
			
				// Add new Card:
				lastCard.after(clonedCard);
			
							
			//                h3.innerHTML += 'We can dynamically change the content.';
							
			//                var elem = document.querySelector('#cardHeader');
			//                
			//                h3.textContent = 'asdfg';
							
			//                var h3 = clonedCard.getElementsByClassName("card-header").innerHTML();
			//                alert(h3.innerHTML);
							
			//                clonedCard.getElementsByClassName(".card-header").innerHTML = "your new header";
			//                clonedCard.getElementsByTagName("h3").innerHTML = "your new header";
			
							// Set text content
							
							// Add text to the end of an element's existing content
			//                elem.textContent += ' Add this after what is already there.';
			
							// Add text to the beginning of an element's existing content
			//                elem.textContent = 'We can add this to the beginning. ' + elem.textContent;
							
			//                var newId = document.querySelector("#card1").cloneNode(true);
			//                newCard.setAttribute("id", "card2");
			//                cards.innerHTML += newCard;
							
			//                let lastElementId = cards.lastElementChild.id;
			//                cards.innerHTML +=
			//                        '<div class="cards">'
			//                + '<div class="card my-5 bg-light" id="'+ lastElementId + '">'
			//                + '<h3 class="card-header">'
			//                + 'Karte 1'
			//                + '</h3>'
			//                + '<div class="grid">'
			//                + '<div class=".g-col-4">'
			//                + '<h4 class="card-header">'
			//                + '<h4 class="card-header">'
			//                + 'Aufgabe'
			//                + '</h4>'
			//                + '<div class="card-body">'
			//                + '<textarea class="form-control" rows="6"></textarea>'
			//                + '</div>'
			//                + '</div>'
			//                + '<div class=".g-col-4">'
			//                + '<h4 class="card-header">'
			//                + 'Lösung'
			//                + '</h4>'
			//                + '<div class="card-body">'
			//                + '<textarea class="form-control" rows="6"></textarea>'
			//                + '</div>'
			//                + '</div>'
			//                + '</div>'
			//                + '</div>'
			//                + ' </div>';
			});
		});
	}
}
/*******************************************************************************
 * bueffeltier/edit-page: page-type-radio-btn-input
 ******************************************************************************/
function showFwdPathInput(option) {
	var textfeld1 = document.getElementById("intFwdPathInputCont");
	var textfeld2 = document.getElementById("extFwdPathInputCont");

	if (option === "option1") {
		textfeld1.style.display = "block";
        textfeld2.style.display = "none";
	} else if (option === "option2") {
		textfeld1.style.display = "none";
		textfeld2.style.display = "block";
	}
}
 /*******************************************************************************
 * 
 ******************************************************************************/