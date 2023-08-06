/**
 * 
 */
//document.addEventListener('DOMContentLoaded', showPageModal("This is an example message.","info"));

///*******************************************************************************
// * Website - Nicht-ASCII-Zeichen
// ******************************************************************************/
//
//var AE = '\u00C4';
//var ae = '\u00E4';
//var OE = '\u00D6';
//var oe = '\u00F6';
//var UE = '\u00DC';
//var ue = '\u00FC';
//var sz = '\u00DF';

/*******************************************************************************
 * Website - Init seitenspezifisch
 ******************************************************************************/

if (window.location.pathname === '/bueffeltier/lesson-editor') {
	
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
		sendPostAjax(
			"http://localhost:8080/bueffeltier/api", 
			jsonData,
			"CREATE_FLASHCARDS",
			successMsg, 
			errorMsg
			);
	});
}

/*******************************************************************************
* Website - Register Save-Buttons and Save-Containers
*******************************************************************************/

document.addEventListener('DOMContentLoaded', function() {
	var saveButtons = document.querySelectorAll('.btn-save');
	saveButtons.forEach(function(saveButton) {
		saveButton.addEventListener('click', function() {
			var containerId = saveButton.getAttribute('data-save-container-id');
			var form = document.getElementById(containerId);
			if (form) {
//				const form = formContainer.querySelector('form');
				sendForm(readFormDataAttributesAndValues(form));
            }
        });
    });
});

/*******************************************************************************
* Website - Autosave
*******************************************************************************/

document.addEventListener("DOMContentLoaded", () => {
	const SAVING_MESSAGE = "Speichern...";
	const SAVED_MESSAGE = "Alle Änderungen wurden gespeichert.";
	document
    .querySelectorAll(".autosave-message")
    .forEach((el) => (el.textContent = SAVED_MESSAGE));
	document.querySelectorAll(".autosave").forEach((inputField) => {
		inputField.addEventListener("change", handleInputChange);
	});
	async function handleInputChange(event) {
		const inputField = event.target;
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

//		try {
//            const response = await fetch(url, {
//            method: "POST",
//            body: formData
//        });

// Ggf. auf Serverantwort reagieren. (Was liefert sendAjaxPost zurück?)

	  	sendPostAjax(url, formData, actionName, successCallback, errorCallback);

		autosaveMessageEl.classList.remove("autosave-message--saving");
		autosaveMessageEl.textContent = SAVED_MESSAGE;
		
//		} catch (error) {
//            console.error("Fehler beim Speichern:", error);
//            // Ggf. auf Fehler bei der Serveranfrage reagieren.
//        }
    }    
});

/*******************************************************************************
* Website - Build FormData
*******************************************************************************/

function readFormDataAttributesAndValues(formElement) {
	const formData = new FormData();
	const divElements = formElement.querySelectorAll('div.form-data');
	divElements.forEach((divElement) => {
		const dataAttributes = divElement.dataset;
		for (const attributeName in dataAttributes) {
			if (dataAttributes.hasOwnProperty(attributeName)) {
				formData.append(attributeName, dataAttributes[attributeName]);
			}
		}
	});
	const inputElements = formElement.querySelectorAll('input, textarea');
	inputElements.forEach((inputElement) => {
		const inputName = inputElement.name;
		const inputValue = inputElement.value;
		formData.append(inputName, inputValue);
	});
	return formData;
}

/*******************************************************************************
 * Website - Build FormData from Form and serialize to JSON or Array
 ******************************************************************************/





//const formData = new FormData();
//formData.append("person[info][name][first]", "John");
//formData.append("person[info][name][last]", "Doe");
//formData.append("person[info][age]", "30");
//formData.append("person[address][city]", "New York");
//formData.append("person[address][country]", "USA");
//
//const result = formDataToJsonAndArray(formData);
//console.log(result.json);

//{
//  "person": {
//    "info": {
//      "name": {
//        "first": "John",
//        "last": "Doe"
//      },
//      "age": "30"
//    },
//    "address": {
//      "city": "New York",
//      "country": "USA"
//    }
//  }
//}

// FormData ist nur für das Senden von Formulardaten geeignet, 
// die standardmäßig im URL-kodierten Formular-Datenformat (application/x-www-form-urlencoded)
// oder im Multiparte-Datenformat (multipart/form-data) übertragen werden sollen.

// Wenn du komplexe Datenstrukturen oder JSON-Daten senden möchtest, wäre es besser, 
// JSON.stringify zu verwenden, um die Daten in JSON zu konvertieren, 
// und den Content-Type-Header auf application/json zu setzen. 

//function formDataToJsonOrArray(formData, returnType) {
//	const obj = {};
//	const arr = [];
//	for (const [key, value] of formData.entries()) {
//		const keys = key.split(/\]\[|\[|\]/).filter(k => k);
//		let currentObj = obj;
//		for (let i = 0; i < keys.length - 1; i++) {
//			const currentKey = keys[i];
//			currentObj[currentKey] = currentObj[currentKey] || {};
//			currentObj = currentObj[currentKey];
//		}
//		currentObj[keys[keys.length - 1]] = value;
//		arr.push(value);
//	}
//	if (returnType === 'json') {
//		return JSON.stringify(obj);
//	} else if (returnType === 'array') {
//		return arr;
//	} else {
//		throw new Error('Invalid returnType. It must be either "json" or "array".');
//	}
//}

//function formDataToJson(formData) {
//    const obj = {
//        tks: []
//    };
//
//    formData.forEach((value, key) => {
//        const keys = key.split('.');
//        let currentObj = obj;
//
//        for (let i = 0; i < keys.length - 1; i++) {
//            const currentKey = keys[i];
//
//            if (currentKey === "tks[]") {
//                currentObj.tks = currentObj.tks || [];
//                currentObj = currentObj.tks[currentObj.tks.length - 1];
//            } else {
//                currentObj[currentKey] = currentObj[currentKey] || {};
//                currentObj = currentObj[currentKey];
//            }
//        }
//
//        currentObj[keys[keys.length - 1]] = value;
//    });
//
//    return JSON.stringify(obj);
//}
//function formDataToJsonOrArray(formData, returnType) {
//    const obj = {};
//    const arr = [];
//
//	// Für jedes Key,Value Paar:
//    for (const [key, value] of formData.entries()) {
//		// Aber nicht für "Action"
//        if (key === "action") {
//            continue;
//        }
//		// Teile den Schlüssel: tsk[].lut[].kut.ktx
//        const keys = key.split('.').filter(k => k);
//        let currentObj = obj;
//
//        for (let i = 0; i < keys.length - 1; i++) {
//            const currentKey = keys[i];
//            const nextKey = keys[i + 1];
//            const isArray = nextKey.endsWith("[]");
//
//            if (isArray) {
//                const arrayKey = nextKey.slice(0, -2);
//                currentObj[currentKey] = currentObj[currentKey] || [];
//                if (!currentObj[currentKey][0]) {
//                    currentObj[currentKey][0] = {};
//                }
//                currentObj = currentObj[currentKey][0];
//            } else {
//                currentObj[currentKey] = currentObj[currentKey] || {};
//                currentObj = currentObj[currentKey];
//            }
//        }
//
//        const lastKey = keys[keys.length - 1];
//        if (lastKey.endsWith("[]")) {
//            const arrayKey = lastKey.slice(0, -2);
//            currentObj[arrayKey] = currentObj[arrayKey] || [];
//            const newObj = {};
//            currentObj[arrayKey].push(newObj);
//            currentObj = newObj;
//        }
//
//        currentObj[lastKey] = value;
//        arr.push(value);
//    }
//
//    if (returnType === 'json') {
//        return JSON.stringify(obj);
//    } else if (returnType === 'array') {
//        return arr;
//    } else {
//        throw new Error('Invalid returnType. It must be either "json" or "array".');
//    }
//}

// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/JSON/stringify#description

//function formDataToJson(formData) {
//    const json = {};
//    let currentObj = json;
//
//    formData.forEach((value, key) => {
//        const keys = key.split('.').filter(k => k);
//        let obj = currentObj;
//
//        for (let i = 0; i < keys.length; i++) {
//            const k = keys[i];
//            if (i === keys.length - 1) {
//                if (k.endsWith('[]')) {
//                    const arrayKey = k.slice(0, -2);
//                    if (!obj[arrayKey]) {
//                        obj[arrayKey] = [];
//                    }
//                    obj[arrayKey].push(value);
//                } else {
//                    obj[k] = value;
//                }
//            } else {
//                if (!obj[k]) {
//                    obj[k] = {};
//                }
//                obj = obj[k];
//            }
//        }
//    });
//
//    return JSON.stringify(json, null, 2);
//}

// Mit Index
// key-value = key
// objekt = objekt.key
// array = 
//formData.forEach((value, key) => {
//		console.log(`${key}: ${value}`);
//	});
	
//function formDataToJson(formData) {
//    const result = {};
//    const objectStack = [];
//
//    for (const [key, value] of formData.entries()) {
//        if (key === "action") {
//            continue;
//        }
//
//        const keys = key.split('.').filter(k => k);
//        let currentObj = result;
//
//        for (let i = 0; i < keys.length; i++) {
//            const currentKey = keys[i];
//            if (!currentObj[currentKey]) {
//                if (i === keys.length - 1 && currentKey.endsWith('[]')) {
//                    currentObj[currentKey.slice(0, -2)] = [];
//                    objectStack.push(currentObj[currentKey.slice(0, -2)]);
//                } else {
//                    currentObj[currentKey] = {};
//                    objectStack.push(currentObj[currentKey]);
//                }
//            }
//            if (i === keys.length - 1) {
//                if (currentKey.endsWith('[]')) {
//                    objectStack[objectStack.length - 1][currentKey.slice(0, -2)].push(value);
//                } else {
//                    currentObj[currentKey] = value;
//                }
//            }
//            currentObj = currentObj[currentKey];
//        }
//    }
//
//    return JSON.stringify(result, null, 2);
//}

function getTestFormData(){
	const formData = new FormData();
	formData.append('tasks[].task{}.name', 'Task1');
	formData.append('tasks[].task{}.text', 'Text1');
	formData.append('tasks[].task{}.name', 'Task2');
	formData.append('tasks[].task{}.text', 'Text2');
	return formData;
}

function formDataToJson(formData) {
	
	formData = getTestFormData();
	formData.forEach((value, key) => {
		console.log(`${key}: ${value}`);
	});
	
	// Result ist ein leeres Objekt:
	const result = {};
	
	// Alle Schlüssel-Wertpaare durchlaufen:
	formData.forEach((value, key) => {
		
		// Der nächste Schlüssel wird in seine Ebenen aufgespalten:
		const keys = key.split('.');
		
		// Das aktuelle Objekt bezieht sich jetzt (wieder) auf die erste Objektebene:
		let currentObj = result;
		
		// Die Ebenen werden durchlaufen:
		for (let i = 0; i < keys.length; i++) {
			
			// Aktuelle Ebene:
			const currentKey = keys[i];
			
			// Ebene ist ein Array:
			if (currentKey.endsWith('[]')) {
				
				// Wenn das Array  im aktuellen Objekt noch nicht vorhanden ist, dann erstelle es:
				const arrayKey = currentKey.slice(0, -2); // TODO: Was repräsentiert der Array-Key?
				if (!currentObj[arrayKey]) {
					currentObj[arrayKey] = [];
				}
				// Wenn das Array auf letzter Objektebene steht, dann wird der Wert zugewiesen:
				if (i === keys.length - 1) {
					currentObj[arrayKey].push(value);
				} else {
					let newArrayIndex;
					// Wenn es im Array noch keinen Eintrag gibt,...
					if (!currentObj[arrayKey][0]) {
						newArrayIndex = 0;
						// ...dann erstelle einen leeren Array-Eintrag. // TODO: Hier wirklich ein Objekt anlegen?
						currentObj[arrayKey][0] = {};
					}else {
  						// Finde den neuen Index für den Eintrag im Array:
  						newArrayIndex = currentObj[arrayKey].length;
  						// Füge ein leeres Objekt nach dem letzten Eintrag hinzu
  						currentObj[arrayKey].splice(newArrayIndex, 0, {});
					}
					
					// Aktuelles Objekt wird neu zugewiesen:
					// Wir springen eine Objektebene weiter, um ggf. Einträge ins leere Array-Objekt zu schreiben.
					currentObj = currentObj[arrayKey][newArrayIndex];
				}
				
			// Ebene ist ein Objekt:
			} else if (currentKey.endsWith('{}')) {
				
				// Wenn das Objekt im aktuellen Objekt noch nicht vorhanden ist, dann erstelle es:
				const classKey = currentKey.slice(0, -2);
				if (!currentObj[classKey]) {
					// ...dann erstelle ein leeres Objekt.
					currentObj[classKey] = {};
				}
				// Aktuelles Objekt wird neu zugewiesen:
				currentObj = currentObj[classKey];
			
			// Ebene ist ein Schlüssel:	
			} else {
				
				// Wenn der Schlüssel auf letzter Objektebene steht, ...
				if (i === keys.length - 1) {
					// dann wird der Wert zugewiesen:
					currentObj[currentKey] = value;
				} else {
					if (!currentObj[currentKey]) {
						currentObj[currentKey] = {};
					}
					// Aktuelles Objekt wird neu zugewiesen:
					currentObj = currentObj[currentKey]; // TODO: Aber macht das hier überhaupt Sinn, den letzen Wert als aktuelles Objekt zu setzen?
				}
			}
		}
	});

	return JSON.stringify(result, null, 2);
}



//function formDataToJson(formData) {
//	
//	formData = getTestFormData();
//	
//	formData.forEach((value, key) => {
//		console.log(`${key}: ${value}`);
//	});
//
//    const result = {};
//
//	// Alle Schlüssel-Wertpaare durchlaufen:
//    for (const [key, value] of formData.entries()) {
//		
//        if (key === "action") {
//            continue;
//        }
//
//		// Der Schlüssel wird in seine Ebenen aufgespalten:
//        const keys = key.split('.').filter(k => k);
//        
//        // Aktuelles Objekt:
//        let currentObj = result;
//
//		// Die Ebenen werden durchlaufen:
//        for (let i = 0; i < keys.length; i++) {
//			
//            const currentKey = keys[i];
//            
//            // Hier wird die Struktur gebaut:
//            
//            // Wenn der Schlüssel im Aktuellen Objekt noch nicht enthalten ist:
//            if (!currentObj[currentKey]) {
//				// Wenn der Schlüssel auf der letzten Ebene ist && wenn der Schlüssel ein Array ist , also ein leeres Array:
//                if (i === keys.length - 1 && currentKey.endsWith('[]')) {
//					// TODO: warum hier etwas abschneiden? Ein Array anlegen! Wozu den ObjectStack?
//					// Dann schneide die Array-Notation am Ende ab.
//                    currentObj[currentKey.slice(0, -2)] = [];
//                // Bei jeder Unterebene:
//                } else {
//					// Wenn es ein Array ist...
//					if(currentKey.endsWith('[]')){
//						//...dann ein Array anlegen.
//						// TODO: prüfen ob es das schon gibt, oder ob was drin ist?
//						 currentObj[currentKey] = [];
//					}else{
//						// Erstelle im aktuellen Objekt eine leere Eigenschaft oder überschreibe sie.
//                    currentObj[currentKey] = {};
//					}
//                    // Füge Objekt mit Eigenschaft dem Objekt-Stack hinzu.
//                }
//            }
//            
//            // Hier werden die Values hinzugefügt:
//            
//            // Immer wenn der Schlüssel auf letzter Ebene ist:
//            if (i === keys.length - 1) {
//				// Und wenn Schlüssel ein Array ist:
//                if (currentKey.endsWith('[]')) {
//					// Dann schneide die Array-Notation am Ende ab.
//                    const arrayKey = currentKey.slice(0, -2);
//                    // Wenn der Schlüssel im Aktuellen Objekt noch nicht enthalten ist:
//                    if (!currentObj[arrayKey]) {
//						// Erstelle ein neues Array im Aktuellen Objekt:
//                        currentObj[arrayKey] = [];
//                    }
//                    // Füge dem Array den Value hinzu.
//                    currentObj[arrayKey].push(value);
//                // ...sonst, wenn der Schlüssel kein Array ist, sondern eine Eigenschaft:
//                } else {
//					// Erstelle eine neue Eigenschaft im Aktuellen Objekt oder überschreibe die vorhandene Eigenschaft:
//                    currentObj[currentKey] = value;
//                }
//            }
//            // Das aktuelle Objekt ist jetzt das 
//            currentObj = currentObj[currentKey];
//        }
//    }
//
//    return JSON.stringify(result, null, 2);
//}

// todo:
// alle Notationen entfernen: []

//function formDataToJson(formData) {
//    const result = {};
//
//    formData.forEach((value, key) => {
//        if (key === "action") {
//            return;
//        }
//
//        const keys = key.split('.').filter(k => k);
//        let currentObj = result;
//
//        keys.forEach((currentKey, index) => {
//            if (currentKey.endsWith('[]')) {
//                currentKey = currentKey.slice(0, -2);
//                if (!currentObj[currentKey]) {
//                    currentObj[currentKey] = [];
//                }
//                if (!currentObj[currentKey][currentObj[currentKey].length - 1]) {
//                    currentObj[currentKey][currentObj[currentKey].length - 1] = {};
//                }
//                currentObj = currentObj[currentKey][currentObj[currentKey].length - 1];
//            } else {
//                if (!currentObj[currentKey]) {
//                    currentObj[currentKey] = {};
//                }
//                currentObj = currentObj[currentKey];
//            }
//
//            if (index === keys.length - 1) {
//                currentObj = value;
//            }
//        });
//    });
//
//    return JSON.stringify(result, null, 2);
//}




function getDataFromDataAttributes(element) {
    const data = {};
    const attributes = element.attributes;
    for (let i = 0; i < attributes.length; i++) {
        const attr = attributes[i];
        if (attr.name.startsWith("data-")) {
            const key = attr.name.replace("data-", "");
            const value = attr.value;
            data[key] = value;
        }
    }
    return data;
}

/*******************************************************************************
 * Website  - Send FormData
 ******************************************************************************/

function sendForm(formData){

	const jsonData = formDataToJson(formData);

//  event.preventDefault(); // Verhindert das Standard-Verhalten des Formulars (Seiten-Neuladen)
	sendPostAjax(
			"http://localhost:8080/bueffeltier/lesson-editor", 
			jsonData,
			"CREATE_FLASHCARDS",
			successMsg, 
			errorMsg
			);

}

    
/*******************************************************************************
 * Website - Send POST AJAX
 ******************************************************************************/

function sendPostAjax(url, jsonData, actionName, successCallback, errorCallback) {
	const formData = new FormData();
	formData.append('action', actionName);
	formData.append('json', jsonData);
	fetch(url, {
		method: 'POST',
		headers: {
			'X-Requested-With': 'XMLHttpRequest'
		},
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

// TODO: Gleiche Behandlung wie bei Submit?
function successMsg(response) {
	alert(response);
}
// TODO: Gleiche Behandlung wie bei Submit?
function errorMsg(error) {
	alert(error);
}

/*******************************************************************************
 * Website  - Send POST SUBMIT
 ******************************************************************************/

function sendPostSubmit(form){
	form.addEventListener("submit", async (event) => {
		event.preventDefault(); // Verhindert das normale Absenden des Formulars
		const formData = new FormData(form);
		try {
			const response = await fetch("http://example.com/api/endpoint", {
				method: "POST",
				body: formData,
			});
	
			if (response.ok) {
				const data = await response.json();
				// Hier kannst du die Serverantwort (data) verarbeiten
			} else {
				console.error("Fehler bei der Serveranfrage:", response.status);
			}
		} catch (error) {
			console.error("Fehler beim Senden der Daten:", error);
		}
	});
}

/*******************************************************************************
* Website - Check Local Storage
******************************************************************************/
// if (global.localStorage) {
//    global.localStorage.setItem('layout', JSON.stringify({
//      [key]: value
//    }));
//  }

/*******************************************************************************
 * Website - Sample JSON
 ******************************************************************************/

//var jsonData = { name: 'John', age: 30 };

/*******************************************************************************
 * Website - Dynamic Cards
 ******************************************************************************/

// Add-Buttons mit Listener
// TODO alle DomContent Loaded zusammen in eine Methode mit Methodenaufrufen.
// Dort wird auch nach relevanz und verwendung gefiltert
document.addEventListener("DOMContentLoaded", function() {
	const addButtons = document.querySelectorAll(".btn-add-dynamic-card");
	addButtons.forEach(function(button) {
		button.addEventListener("click", function() {
			addCardToDynamicContainer(button);
		});
	});
	const addCardOutsideButton = document.querySelector(".btn-main-add-dynamic-card");
  if (addCardOutsideButton) {
    addCardOutsideButton.addEventListener("click", function() {
      addCardToDynamicContainerOutside(addCardOutsideButton);
    });
  }
});

// Del-Buttons mit Listener
document.addEventListener("DOMContentLoaded", function() {
	const delButtons = document.querySelectorAll(".btn-del-dynamic-card");
	delButtons.forEach(function(button) {
		button.addEventListener("click", function() {
			delCardFromDynamicContainer(button);
		});
	});
});

function addEventListenersToCardButtons(card) {
	const addButton = card.querySelector(".btn-add-dynamic-card");
	if (addButton) {
		addButton.addEventListener("click", function() {
			addCardToDynamicContainer(addButton);
		});
	}
	const delButton = card.querySelector(".btn-del-dynamic-card");
	if (delButton) {
		delButton.addEventListener("click", function() {
			delCardFromDynamicContainer(delButton);
		});
	}
}

function addCardToDynamicContainer(button) {
	const mainContainer = button.closest(".dynamic-cards-main");
	const cardsContainer = mainContainer.querySelector(".dynamic-cards-cards");
	const cardOrigin = button.closest(".dynamic-card");
	const prototypeContainer  = mainContainer.querySelector(".dynamic-cards-prototype");
	const prototypeCard = prototypeContainer.querySelector(".dynamic-card");
	const maxLength = mainContainer.dataset.dynamicCardsMaxLength || Infinity;
	if(cardsContainer.children.length >= maxLength){
		showPageModal("Die Maximale Kartenanzahl beträgt " + maxLength + ".", "info")
		return;
	}
	if (cardOrigin && prototypeCard && cardsContainer.children.length < maxLength) {
		const clonedCard = prototypeCard.cloneNode(true);
		addEventListenersToCardButtons(clonedCard);
		if (cardOrigin.nextSibling) {
			cardOrigin.parentElement.insertBefore(clonedCard, cardOrigin.nextSibling);
		} else {
			cardOrigin.parentElement.appendChild(clonedCard);
		}
		rearrangeDynamicCardsIds(cardsContainer,1);
		fadeInElement(clonedCard);
	}
}

function addCardToDynamicContainerOutside(button) {
	const mainContainer = button.closest(".dynamic-cards-main");
	const cardsContainer = mainContainer.querySelector(".dynamic-cards-cards");
	const prototypeContainer  = mainContainer.querySelector(".dynamic-cards-prototype");
	const prototypeCard = prototypeContainer.querySelector(".dynamic-card");
	const maxLength = mainContainer.dataset.dynamicCardsMaxLength || Infinity;
	if(cardsContainer.children.length >= maxLength){
		showPageModal("Die Maximale Kartenanzahl beträgt " + maxLength + ".", "info")
		return;
	}
	if (prototypeCard && cardsContainer.children.length < maxLength) {
		const clonedCard = prototypeCard.cloneNode(true);
		addEventListenersToCardButtons(clonedCard);
		var lastCard = cardsContainer.lastElementChild;
		if (lastCard) {
            cardsContainer.insertBefore(clonedCard, lastCard.nextSibling);
        } else {
            cardsContainer.prepend(clonedCard);
        }
		rearrangeDynamicCardsIds(cardsContainer,1);
		fadeInElement(clonedCard);
	}
}

function delCardFromDynamicContainer(button) {
	const cardsContainer = button.closest(".dynamic-cards-cards");
	const dynamicCard = button.closest(".dynamic-card");
	if (dynamicCard) {
		fadeOutElement(dynamicCard);
		setTimeout(() => {
			dynamicCard.remove();
			rearrangeDynamicCardsIds(cardsContainer,1);
		}, 500);
	}
}

/*******************************************************************************
 * Website - Dynamic Cards - CopyAndIncrementElement
 ******************************************************************************/

function rearrangeDynamicCardsIds(parentContainer, startNumber) {
	const dynamicCards = parentContainer.querySelectorAll(".dynamic-card");
	for (let i = 0; i < dynamicCards.length; i++) {
		const dynamicCard = dynamicCards[i];
		const elementsToUpdate = dynamicCard.querySelectorAll(".dynamic-id");
		elementsToUpdate.forEach(function(element) {
			// Ändern der ID des Elements
			element.id = updateIdSuffixInString(element.id, startNumber);
			// Ändern des Inner Html des Elements
//			const originalText = getLastOwnTextNodeTextContent(element);
			let updatedText = null;
			const originalText = getInnerTextIfNoChildrenOpt(element);
			if(originalText){
				updatedText = updateIdSuffixInString(originalText, startNumber);
			}
			if(updatedText){
				element.innerText = updatedText; 
			}
			// Ändern der Attribute "data-" und "aria-"
			const attributes = element.attributes;
			for (let j = 0; j < attributes.length; j++) {
				const attribute = attributes[j];
				if (attribute.name.startsWith('data-') || attribute.name.startsWith('aria-')) {
					element.setAttribute(attribute.name, updateIdSuffixInString(attribute.value, startNumber));
				}
			}	
		});
		startNumber++;
	}
}

function getInnerTextIfNoChildrenOpt(element) {
	if (element.childNodes.length === 1 && element.childNodes[0].nodeType === Node.TEXT_NODE) {
		return element.innerText.trim();
	} else {
		return null;
	}
}

// Funktion, um den Textinhalt eines Elements ohne Kinder zu erhalten
//function getTextContentWithoutChildren(element) {
//	const clone = element.cloneNode(true);
//	const childElements = clone.querySelectorAll("*");
//	childElements.forEach((child) => {
//		if (child !== element) {
//			child.textContent = "";
//		}
//	});
//	return clone.textContent.trim();
//}

function getLastOwnTextNodeTextContent1(element){
	// 1. alle Kinder und Kindeskinder abrufen.
	// In welcher Form bekomme ich sie? Wie kann ich die erste Ebene Filtern?
	const childNodes = element.childNodes;
	
	// X. achtung, damit, content fälschlich als leer zu erkennen und dann zu
	// setzen
	
	// ersetzte nur den text in html elementen, wenn diese keine weiteren kinder haben
	// und wenn sie mit dynamic-id ausgezeichnet sind. 
	
	
	const directChildren = element.children;
	
	for (let i = directChildren.length -1; i >= 0; i--) {
		const node = directChildren[i];
//		if(node.nodeType === Node.TEXT_NODE && node.textContent.trim() !== ""){
		nodeType = node.nodeType;
		nodeValue = node.nodeValue;
		if(node.nodeType === Node.TEXT_NODE && node.nodeValue.trim() !== ""){
			
//			return lastInnerTextNodeClone.innerText.trim();
			return lastInnerTextNodeClone.nodeValue.trim();
		}
	}
	return "";
}

function getLastOwnTextNodeTextContent(element){
	const directChildren = element.children;
	
	for (let i = directChildren.length -1; i >= 0; i--) {
		const node = directChildren[i];
//		if(node.nodeType === Node.TEXT_NODE && node.textContent.trim() !== ""){
		nodeType = node.nodeType;
		nodeValue = node.nodeValue;
		if(node.nodeType === Node.TEXT_NODE && node.nodeValue.trim() !== ""){
			
//			return lastInnerTextNodeClone.innerText.trim();
			return lastInnerTextNodeClone.nodeValue.trim();
		}
	}
	return "";
}

function replaceLastOwnTextNodeTextContent(element, newText){
	const nodes = element.children;
	for (let i = nodes.length - 1; i >= 0; i--) {
		if(nodes[i].nodeType === Node.TEXT_NODE){
//			nodes[i].innerText = newText;
			nodes[i].nodeValue = newText;
			break;
		}
	}
	return element;
}

/**
 * @param {string} oldString - Die ursprüngliche Zeichenkette.
 * @param {number} newSuffix - Das neue Suffix (Muss eine Ganzzahl sein).
 * @returns {string} Die aktualisierte Zeichenkette mit dem neuen Suffix.
 */
function updateIdSuffixInString(oldString, newSuffix){
	let newString;
	if (newSuffix === 0) {
		newString = oldString.replace(/\d+$/, "0");
	}else{
		newString = oldString.replace(/\d+$/, newSuffix.toString());
	}
	return newString; 
}

/*******************************************************************************
 * Website - Fade-In and -Out
 ******************************************************************************/

function fadeInElement(element) {
	element.style.opacity = 0;
	element.style.display = "block";
	let startTime = null;
	function fadeInStep(timestamp) {
		if (!startTime) startTime = timestamp;
		const progress = timestamp - startTime;
		const duration = 500; // Dauer der Animation in Millisekunden (hier 0.5 Sekunden).
		const opacity = Math.min(1, progress / duration);
		element.style.opacity = opacity;
		if (progress < duration) {
			requestAnimationFrame(fadeInStep);
		}
	}
	requestAnimationFrame(fadeInStep);
}

function fadeOutElement(card) {
	card.style.transition = "opacity 0.5s";
	card.style.opacity = 0;
}

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

/*******************************************************************************
 * 
 ******************************************************************************/
 
/*******************************************************************************
 *  
 ******************************************************************************/