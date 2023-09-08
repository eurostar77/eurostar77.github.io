const vokabeln = [
    ["Spanisch lernen", "Aprender español"],
    ["Guten Morgen", "Buenos días"],
    ["Nett Sie kennenzulernen", "Encantado de conocerle"],
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
