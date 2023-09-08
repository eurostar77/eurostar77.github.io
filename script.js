const vokabeln = [
    ["Hallo!","¡Hola!"],
["Guten Morgen!; Guten Tag!","¡Buenos días!"],
["Wie geht´s?","¿Qué tal?"],
["gut","bien"],
["Danke.","Gracias."],
["und","y"],
["Und du?","¿Y tú?"],
["auch","también"],
["Ich bin …","Yo soy…"],
["Ich heiße …","Me llamo…"],
["Wie heißt du?","¿Cómo te llamas?"],
["Wie …?","¿Cómo…?"],
["ja","sí"],
["von; aus","de"],
["Woher …?","¿De dónde…?"],
["Woher kommst du?","¿De dónde eres?"],
["Deutschland","Alemania"],
["Auf Wiedersehen!; Tschüs!","¡Adiós!"],
["Bis später!","¡Hasta luego!"],
["sehr","muy"],
["So so.; Es geht.","Así así."],
["schlecht","mal"],
["Guten Tag!; Guten Abend!","¡Buenas tardes!"],
["Guten Abend!; Gute Nacht!","¡Buenas noches!"],
];

let currentVokabelIndex = 0;
const deutscheVokabelDiv = document.getElementById("deutscheVokabel");
const spanischeVokabelDiv = document.getElementById("spanischeVokabel");

function loadNextVokabel() {
    if (currentVokabelIndex < vokabeln.length - 1) {
        currentVokabelIndex++;
    } else {
        currentVokabelIndex = 0;
    }
    updateVokabelAnzeige();
}

function updateVokabelAnzeige() {
    const [deutscheVokabel, spanischeVokabel] = vokabeln[currentVokabelIndex];
    deutscheVokabelDiv.textContent = deutscheVokabel;
    spanischeVokabelDiv.textContent = spanischeVokabel;
    deutscheVokabelDiv.style.display = "block";
    spanischeVokabelDiv.style.display = "none";
}

// Buttons
document.getElementById("btn-question").addEventListener("click", function() {
    deutscheVokabelDiv.style.display = "block";
    spanischeVokabelDiv.style.display = "none";
});

document.getElementById("btn-answer").addEventListener("click", function() {
    deutscheVokabelDiv.style.display = "none";
    spanischeVokabelDiv.style.display = "block";
});

document.getElementById("btn-play").addEventListener("click", function() {
    responsiveVoice.speak(vokabeln[currentVokabelIndex][1], "Spanish Female");
});

document.getElementById("btn-next").addEventListener("click", function() {
    loadNextVokabel();
});

updateVokabelAnzeige();
