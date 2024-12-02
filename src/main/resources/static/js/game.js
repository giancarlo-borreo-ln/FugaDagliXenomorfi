const BASE_URL = 'http://localhost:8080'; // URL del backend

// Avanza turno
document.getElementById("advance-turn-button").addEventListener("click", () => {
    fetch(`${BASE_URL}/game/turn`, {
        method: 'PUT', // Metodo HTTP
    })
    .then(response => {
        if (response.ok) {
            alert("Turno avanzato con successo!");
            visualizzaStatoPartita(); // Aggiorna lo stato della partita
        } else {
            alert("Errore durante l'avanzamento del turno.");
        }
    })
    .catch(error => {
        console.error("Errore durante la richiesta:", error);
        alert("Si è verificato un errore. Riprova più tardi.");
    });
});

// Visualizza stato della partita
document.getElementById("game-status-button").addEventListener("click", () => {
    visualizzaStatoPartita();
});

function visualizzaStatoPartita() {
    fetch(`${BASE_URL}/game/status`) // Chiamata all'endpoint dello stato
        .then(response => {
            if (response.ok) {
                return response.json(); // Converte la risposta in JSON
            } else {
                throw new Error("Errore durante il recupero dello stato della partita.");
            }
        })
        .then(data => {
            // Usa i dati JSON per aggiornare il DOM
            const statusContainer = document.getElementById("game-status-container");
            statusContainer.innerHTML = `
                <h2>Stato della Partita</h2>
                <p><strong>Turno Corrente:</strong> ${data.turnoCorrente}</p>
                <p><strong>Umani Vivi:</strong> ${data.umaniVivi}</p>
                <p><strong>Xenomorfi Presenti:</strong> ${data.xenomorfiPresenti}</p>
                <p><strong>Moduli Riparati:</strong> ${data.moduliRiparati}</p>
            `;
        })
        .catch(error => {
            console.error("Errore durante la richiesta:", error);
            alert("Non è stato possibile recuperare lo stato della partita.");
        });
}

// Termina la partita e torna alla schermata principale
document.getElementById("end-game-button").addEventListener("click", () => {
    if (confirm("Sei sicuro di voler terminare la partita?")) {
        alert("Partita terminata. Tornerai alla schermata principale.");
        window.location.href = "index.html"; // Torna alla pagina principale
    }
});
