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
["Woher …?","¿De dónde… ?"],
["Woher kommst du?","¿De dónde eres?"],
["Deutschland","Alemania"],
["Auf Wiedersehen!; Tschüs!","¡Adiós!"],
["Bis später!","¡Hasta luego!"],
["sehr","muy"],
["So so.; Es geht.","Así así."],
["schlecht","mal"],
["Guten Tag!; Guten Abend!","¡Buenas tardes!"],
["Guten Abend!; Gute Nacht!","¡Buenas noches!"],
["Wie ist dein Nachname?","¿Cómo es tu apellido?"],
["er/sie/es ist","es"],
["der Nachname","el apellido"],
["Man schreibt …","Se escribe…"],
["Wie schreibt man …?","¿Cómo se escribe… ?"],
["mit Akzent","con acento"],
["mit Umlaut","con dos puntos"],
["Doppel-","doble"],
["der Park","el parque"],
["das Hotel","el hotel"],
["das (Stadt) Zentrum","el centro"],
["der Zoo","el zoo"],
["das Restaurant","el restaurante"],
["das Museum","el museo"],
["der Platz","la plaza"],
["die Buchhandlung","la librería"],
["das Haus; die Wohnung","la casa"],
["die Nummer; die Zahl","el número"],
["der Fuchs","el zorro"],
["der Hund","el perro"],
["die Katze","el gato"],
["der Elefant","el elefante"],
["der Bär","el oso"],
["das Pferd","el caballo"],
["der Esel","el burro"],
["die Biene","la abeja"],
["die Vogelspinne; die Tarantel","la tarántula"],
["der Affe","el mono"],
["der Frosch","la rana"],
["die Kuh","la vaca"],
["Ich bin ... Jahre alt.","Tengo... años."],
["mein; meine","mi"],
["das Handy","el móvil"],
["das Telefon","el teléfono"],
["aber","pero"],
["die Stadt","la ciudad"],
["jetzt","ahora"],
["Wie alt bist du?","¿Cuántos años tienes?"],
["die Welt","el mundo"],
["die Schule","el colegio"],
["die Mutter","la madre"],
["der Vater","el padre"],
["die Eltern","los padres (pl.)"],
["der Bruder / die Schwester","el hermano / la hermana"],
["die Geschwister","los hermanos"],
["der Freund / die Freundin","el amigo / la amiga"],
["das Haustier","la mascota"],
["Er / Sie heißt …","Se llama…"],
["dein / deine","tu"],
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
