/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//document.addEventListener("DOMContentLoaded", function () {
//
//
//var card = document.getElementById("card");
//get("card");

//var back = document.getELementById("back");

var flipBtn;
var front;
var back;
var showBack = false;
var btnAnswerRight;
var btnAnswerWrong;

// let cards;
let addBtn;
var modelCard;

var cardCount;

window.onload = function () {


/*******************************************************************************
 * FlipCard Flip Button
 ******************************************************************************/
flipBtn = document.getElementById("flipBtn");

if (flipBtn!==null)
{
	flipBtn.addEventListener("click", function()
        {
            front = document.getElementById("front");
            back = document.getElementById("back");
            
            if (showBack)
            {
	            front.classList.remove("hidden");
	            back.classList.add("hidden");
	            showBack = false;
	            
            } else
            {
                front.classList.add("hidden");
                back.classList.remove("hidden");
                showBack = true;
            }
        }
    );
}



//// FlipCard Answer Buttons:
//    btnAnswerRight = document.getElementById("btnAnswerRight");
//    btnAnswerRight.addEventListener("click", function()
//        {
//            // wenn btn geklickt, aktuellen status prüfen und wechseln
//            // den anderen button auch wechseln
//            // Button Farben anpassen.
//            console.log(btnAnswerRight.classList.toString());
//            btnAnswerRight.classList.add("btn btn-secondary");
//        
//        
//
//        }
//    );
};

// Flip Card Buttons Antwort richtig / falsch
//window.onload = function () {
//    
//    
//};

    

 



//var btn = document.getElementById("flipBtn");
//if(btn){
//    btn.addEventListener("click", function() {
//        alert("Hallo Welt!");
//        document.getELementById("demo").innerHTML = "Hello World";
//        front.classList.add("hidden");
//        }, false
//    );
//}

//$(window).ready(()=>
//{
//     var btn = document.getElementById("flip");
//    if(btn){
//        btn.addEventListener("click", function()
//            {
//                test();
//                front.classList.add("hidden");
//            }, false
//        );
//    }
//}
//);



//wofür? ajax?
//const btn = document.querySelector(".btn");
//
////btn.addEventListener("click", (e) => {
////        e.preventDefault();
////        console.log(e);
////    }    
////);
//
//btn.addEventListener("click", (e) => {
//        e.preventDefault();
//        console.log(e.target.className);
////        console.log(e.target.id);
//    }    
//);




//test für msg-box
//
////document.querySelector("#open-dialog").addEventListener("click", toggleDialog);
//
//function toggleDialog(){
//	var dialog = document.querySelector("dialog"),
//    	closebutton = document.getElementById("close-dialog"),
//    	pagebackground = document.querySelector("body");
//			
//	if (!dialog.hasAttribute("open")) {
//            
//            var div = document.createElement("div");
////            window.alert("sometext");
//            div.id = "backdrop";
//            document.body.appendChild(div);
//            
//            
//            // show the dialog 
//            dialog.setAttribute("open","open");
//            // after displaying the dialog, focus the closebutton inside it
//            closebutton.focus();
//            closebutton.addEventListener("click", toggleDialog);
//            
//            
//             
//	}
//	else {		
//            dialog.removeAttribute("open"); 
//            var div = document.querySelector("#backdrop");
//
//
//            div.parentNode.removeChild(div);
//		lastFocus.focus(); 
//	}
//    }
//test für msg-box



//Test für Message-Box-1
    function dlgCancel(artId){
        dlgHide(artId);
//        document.getElementsByTagName("H1")[0].innerHTML = "You clicked Cancel.";
    }

    function dlgOK(){
//        alert("method:dlgOK");
        dlgHide();
//        document.getElementsByTagName("H1")[0].innerHTML = "You clicked OK.";
    }

    function dlgHide(artId){
        var whitebg = document.getElementById("white-background");
        var dlg = document.getElementById("dlgbox-" + String(artId));
        whitebg.style.display = "none";
        dlg.style.display = "none";
    }
    
    function getDialog(artId){
        showDialog();
        
    }
    
    var selectedArticle = -1;
    
    
    
    // todo: Bestimmte Methoden nur in bestimmten Seiten laden und chachen
    //       Andere Methoden für alle Seiten cachen.
//    function deletePage(pageId){
//            
//            
//            deletePage();
//            showDialog();
//            if true finalyDeletePage();
//            else;
//        
//        
//        // showDialog()
//        var whitebg = document.getElementById("white-background");
//        var dlg = document.getElementById("dlgBoxX");
//        
//        whitebg.style.display = "block";
//        dlg.style.display = "block";
//        
//        var winWidth = window.innerWidth;
//        
//        dlg.style.left = (winWidth/2) - 480/2 + "px";
//        dlg.style.top = "150px";
//    }
    
//    function deleteArticle{
//
//    } 
    
    
    function showDeletePageDialog(artId){
//    function showDialog(artId){
        
//        var artId;
//        artId = Number(NumVal);
        
//        alert(artId);
//        alert("function:showDialog");
        var whitebg = document.getElementById("white-background");
        var dlg = document.getElementById("dlgbox-" + String(artId));
        
        whitebg.style.display = "block";
        dlg.style.display = "block";

        var winWidth = window.innerWidth;

        dlg.style.left = (winWidth/2) - 480/2 + "px";
        dlg.style.top = "150px";
    }


/******************************************************************************/
/*                            Cookie Management                               */
/******************************************************************************/

function createCookie(c_name,c_value,c_days) {
      if (c_days) {
            var c_date = new Date();
            c_date.setTime(c_date.getTime() + (c_days*24*60*60*1000));
            var c_expires = "expires=" + c_date.toGMTString();
      } else {
            var c_expires = "";
      }
      document.cookie = c_name + '=' + c_value + ';' + c_expires + "; path=/";
}

function readCookie(name) {
	
      var nameEQ = name + "=";
      
      var cookies = document.cookie.split(';');
      
      for(var i = 0; i < cookies.length; i++) {
	
            var c = cookies[i];
            
            while (c.charAt(0)==' ') c = c.substring(1,c.length);
            
            if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
      }
      return null;
}

function eraseCookie(name) {
	
      createCookie(name,"",-1);
     
}

/******************************************************************************/
/*                         Setup Cookie Preferences                           */
/******************************************************************************/

var areCookiesConfirmed = readCookie('bueffeltier-privacy');

var acceptButton = document.getElementById('cookie-consent-banner_btn_accept');

var cookieBanner = document.getElementById('cookie-consent-banner');

if (areCookiesConfirmed != 'true') {
	
     showCookieBanner(true);
     
} else {
	
     showCookieBanner(false);

      loadConsentRequiredCookies();
}

// Reset Settings
/*
var resetButton = document.getElementById('edit_cookie_settings');
resetButton.addEventListener('click', function () {
      showCookieConsentBanner();
      console.log('Reset Cookie Preferences...');
   });
*/

function showCookieBanner(isVisible) {
	
	if(isVisible){
		
     cookieBanner.classList.remove('js_cookie-consent-banner_hidden');
     
	}else{
		
     cookieBanner.classList.add('js_cookie-consent-banner_hidden');
     
	}

     // var trackingCookiesCheckbox = document.getElementById('tracking_cookies');
     
     // if (trackingCookiesCheckbox.checked) {
           // createCookie('trackingCookies', 'true', 14);
      // }
}

//Cookies Consent Screen Buttons
/*
var Settings = document.getElementById('cookie_pop_up_settings');
var General = document.getElementById('cookie_pop_up');
var MoreInfo = document.getElementById('cookies_more_info');
var TrackingCookiesCheckbox = document.getElementById('tracking_cookies');
MoreInfo.addEventListener('click', function () {
      General.classList.add('cookie_hide');
      Settings.classList.remove('cookie_hide');
      Settings.classList.add('cookie_show');
      console.log('Opened Cookie Settings');
   });
*/

//Validate Checkbox of Tracking Cookies
function validateTrackingCookies() {
      if (TrackingCookiesCheckbox.checked) {
            console.log('Tracking Cookies selected');
            createCookie('trackingCookies', 'true', 14);
      } else {
            console.log('Deselected Tracking Cookies');
            createCookie('trackingCookies', 'false', 14);
      }
}


// Save Cookie Settings


acceptButton.addEventListener('click', function () {
	
      cookieBanner.classList.add('js_cookie-consent-banner_hidden');
      
      createCookie('bueffeltier-privacy', 'true', 14);
      
      console.log('Accepted Cookie Usage');
      
      loadConsentRequiredCookies();
   });  

/*
SaveButton.addEventListener('click', function () {
//      CookieConsentOverlay.classList.add('js_cookie-consent-banner_hidden');
      createCookie('bueffeltier-privacy', 'true', 14);
      console.log('Saved Cookie Settings');
      loadCookies();
   });
   */

/**********************************************************/
/*                    Cookie Functions                    */
/**********************************************************/

//GA Function
function activateGoogleAnalytics() {
      //GA Code
      console.log('Google Analytics loaded successfully...');
}

/**********************************************************/
/*                   Cookie Categories                    */
/**********************************************************/

function loadConsentRequiredCookies() {
	
      var trackingCookies = readCookie('trackingCookies');
      switch (trackingCookies) {
	
            case 'true':
                  //Google Analytics
                  activateGoogleAnalytics();
                  console.log('Tracking Cookies loaded successfully...');
                  break;
            
            case 'false':
                  console.log('Tracking Cookies not loaded... (not selected)');
                  break;
      }
}

/*
//Delete Cookies (Dev only)
eraseCookie('bueffeltier-privacy');
eraseCookie('trackingCookies');
*/

/******************************************************************************/
/*                              Site Structure                                */
/******************************************************************************/




// seiten haben id

// zu seiten id kann artikel nachgeladen werden

// 

function loadArticles(pageId){
	
    //alert("Lade Artikel zur Seite " + pageId);
    
	var xhr = new XMLHttpRequest();
	
	xhr.open("GET", "http://localhost:8080/bueffeltier/api?id=" + pageId, true);
	
	xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");
	
	xhr.onreadystatechange = function(){
		
			if (xhr.readyState === 4 && xhr.status === 200) {
				
	    	var articles = JSON.parse(xhr.responseText);
	    	
	    	//console.log(articles);
	    	
	    	//console.dir(articles);
	    	
	    	//console.log(articles["0"]["title"]);

			//alert(articles.);
			
			var article = articles["0"];
	    	
	    	var pageContext = document.getElementById('page-context-'+pageId);
	    	
	    	var articleDiv = buildArticleRow(article.title, article.id)
	    	user
	    	pageContext.appendChild(articleDiv);
            //document.body.appendChild(div);
	    	
	    	return articles;
	    	// Do something with the articles, such as rendering them on the page
	  	}
	};
	xhr.send();
}

function buildArticleRow(name, id){
	
	var p = document.createElement("p");
	p.className = "article-title";
	p.innerHTML = "ID: " + id + " " + name;
	
	var articleDiv = document.createElement("div");
	articleDiv.className = "article-row";
	articleDiv.id = "article-"+id;
	
	articleDiv.appendChild(p);
	articleDiv.appendChild(buildButtonGroup(id));
	
	return articleDiv;
}

function buildButtonGroup(id){
	
	// edit
	var editButton = document.createElement("button");
	editButton.id = "edit-"+id;
	var buttonText = document.createTextNode("Edit Article");
	editButton.appendChild(buttonText);
	
	
	// delete
	// move
	// publish
	// insert
	
	var btnGroupDiv = document.createElement("div");
	btnGroupDiv.className = "article-buttons";
	btnGroupDiv.appendChild(editButton);
	
	return btnGroupDiv;
}

function editPage(id){
	 window.location.href = "https://www.example.com";
}

/*******************************************************************************
* File Input
*******************************************************************************/
//const form = document.getElementById('fileExplorer');
//const uploadFileInput = document.getElementById('uploadFileInput');
//const uploadFileButton = document.getElementById('uploadFileButton');
//
//uploadFileButton.addEventListener('click', () => {
//    uploadFileInput.click();
//  });
//
//uploadFileInput.addEventListener('change', () =>
//{
//    form.enctype = 'multipart/form-data';
//    form.submit();
//    // form.enctype = null;
//});







//const form = document.getElementById('fileExplorer');
//const fileInput = document.getElementById('uploadFileInput');

//function uploadFile()
//{
//	//var input = document.createElement('input');
//	//input.type = 'file';
//	//input.click();
//	 fileInput.click();
//	
//	form.enctype = 'multipart/form-data';
//	
//	fileInput.addEventListener('change', () => 
//	{
//        form.submit();
//        
//        form.enctype = null;
//    });
//    
//    fileInput.addEventListener("abort", ()=>{
//		
//		form.enctype = null;
//	});
//    
//}

//function updateFileName() {
////  var fileInput = document.getElementById('file-input');
//  var fileNameInput = document.getElementById('fileNameInput');
//  
//  // Prüfen, ob eine Datei ausgewählt wurde
//  if (fileInput.files.length > 0) {
//    fileNameInput.value = fileInput.files[0].name;
//  }
//}

//function newFolderModalApprove() {
//
//	fileInput.click();
////  var fileInput = document.getElementById('fileInput');
//
//  if (fileInput.files.length > 0) {
//    var form  = document.getElementById('fileExplorer');
////    form.action = 'upload.php';
//	form.enctype = 'multipart/form-data';
//    form.submit();
//	form.enctype = null;
//  } else {
////	form.enctype = null;
//  }
//  }




//function submitForm() {
//	
//	const form = document.getElementById('fileExplorer');
//	form.enctype = 'multipart/form-data';
//	
//	const fileInput = fileInput.addEventListener('change', () => {
////        form.action = '/upload'; // Set the server endpoint to upload the file
//        form.submit(); // Submit the form
//    });
//	
//	document.getElementById('fileExplorer').submit();
//}

//	// Prüfe, ob Ordner schon existiert
////	modal abbrechacceptButton
////	newFolderInput.value = 'testFolder1';
//	
////	form.submit();
//	
//	newFolderInput.value = '';
//}
	

/*******************************************************************************
 * Input-Group 
 */

// 4. abbrechen button
// unabhängige klassennamen aus enum p.ä.
// defaul settings definieren und zurücksetzen ermöglichen pro eigenschaft oder für alle



const addButtons = document.querySelectorAll('.input-group-add-btn');
	addButtons.forEach(function(button) {
		button.addEventListener('click', addInputGroup);
});

const delButtons = document.querySelectorAll('.input-group-del-btn');
	delButtons.forEach(function(button) {
	button.addEventListener('click', removeInputField);
});

function removeInputField(event) {
	var inputGroup = event.target.closest('.input-group');
	var inputFieldsContainer = inputGroup.closest('.inputFieldsContainer');
	
	if(inputFieldsContainer.querySelectorAll('.input-group').length>1){
		inputFieldsContainer.removeChild(inputGroup);
	}else{
		inputGroup.querySelector('input').value = '';
	};
}

function addInputGroup(event) {
	const inputFieldsContainer = event.target.closest('.inputFieldsContainer');
	const inputElements = inputFieldsContainer.getElementsByTagName("input");
	const maxlength = inputFieldsContainer.getAttribute("data-maxLength");
	if(inputElements.length < maxlength){

		const newInputGroup = document.createElement('div');
		newInputGroup.classList.add('input-group');
	
	  // Ein neues input-Element erstellen
		const newInput = document.createElement('input');
		newInput.setAttribute('type', 'text');
		const lastInputElement = inputElements[inputElements.length - 1];
//		var index = parseInt(
//			lastInputElement.name.substring(lastInputElement.name.length -2)
//			);
//		var index;
//		const regex = /\[(\d+)\]/;
//		const match = lastInputElement.name.match(regex);
//		if (match) {
//			index = parseInt(match[1])+1;
//		}
//
//		var start = lastInputElement.name.indexOf('[');
//		var end = lastInputElement.name.indexOf(']');
//		const newInputName = lastInputElement.name.substring(0, start+1) + index.toString() + lastInputElement.name.substring(end);
//
//		newInput.setAttribute('name', newInputName);
//		newInput.setAttribute('name', lastInputElement.name.replace(']',index.toString()+']'));
		newInput.setAttribute('name', lastInputElement.name);
		newInput.setAttribute('class', 'form-control mb-3');
		newInput.setAttribute('aria-describedby', 'text');
	
	  // Ein neues Löschen-Button-Element erstellen
		const newButton = document.createElement('button');
		newButton.classList.add('btn', 'btn-outline-secondary', 'input-group-del-btn');
		newButton.setAttribute('type', 'button');
		newButton.textContent = '-';
	
	  // Ein neues Div-Element erstellen und das Input- und Button-Element darin platzieren
		const newDiv = document.createElement('div');
		newDiv.classList.add('input-group-append');
		newDiv.appendChild(newButton);
	
	  // Das Input- und Div-Element in die neue input-group einfügen
		newInputGroup.appendChild(newInput);
		newInputGroup.appendChild(newDiv);
	
	  // Den Event Listener zum Löschen-Button hinzufügen
		newButton.addEventListener('click', removeInputField);
	
	  // Die neue input-group am Ende des inputFieldsContainers einfügen
		inputFieldsContainer.insertBefore(newInputGroup, inputFieldsContainer.lastElementChild);
	
	  // Event Listener für die Löschen-Buttons aktualisieren
		const delButtons = document.querySelectorAll('.input-group-del-btn');
		delButtons.forEach(function(button) {
			button.removeEventListener('click', removeInputField);
	    	button.addEventListener('click', removeInputField);
		});
	}
}

/*******************************************************************************
 * Form Data Submission
*/

let formContent = new Map();

formContent



/*******************************************************************************
 * Form Input Validation
*/

//var input = document.getElementById("password");
//input.addEventListener("input", function(event) {
//  if (input.value.length < 8) {
//    input.setCustomValidity("Das Passwort muss mindestens 8 Zeichen lang sein.");
//  } else {
//    input.setCustomValidity("");
//  }
//});

/*******************************************************************************
 * FormControl File Chooser:
*/
// Hier das ID-Attribut des App Filechooser-Elements ersetzen
//const appFilechooser = document.getElementById("app-filechooser");

// Alle Elemente mit der Klasse "fc-internal" auswählen
const internalFilechoosers = document.querySelectorAll(".fc-internal");

// Event-Listener hinzufügen, um internen App Filechooser zu öffnen
internalFilechoosers.forEach(function (internalFilechooser) {
	
	internalFilechooser.addEventListener("click", function () { 
		
		event.preventDefault();
		//event.stopPropagation();
		alert("Huhu!");
//		window.location = "/bueffeltier/ressources";
		//appFilechooser.click();
		
//		var url = "/bueffeltier/ressources";
//		var params = {
//			param1: "value1",
//			param2: "value2"
//		};
//
//		fetch(url, {
//			method: 'POST',
//			headers: {
//				'Content-Type': 'application/json'
//			},
//  			body: JSON.stringify(params)
//		})
//		.then(function(response) {
//	  		return response.text();
//		})
//		.then(function(data) {
//			console.log(data);
//		})
//		.catch(function(error) {
//			console.log('Fehler:', error);
//		});
	});
});





 