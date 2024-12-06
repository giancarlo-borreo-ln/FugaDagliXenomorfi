# Fuga dagli Xenomorfi

**Fuga dagli Xenomorfi** è un gioco di simulazione strategica e di sopravvivenza, in cui i giocatori devono gestire risorse, proteggere i membri dell'equipaggio e fuggire da un'astronave infestata da Xenomorfi. Il gioco include gestione strategica delle risorse, controllo della popolazione e esplorazione dinamica della mappa, mentre i giocatori cercano di riparare l'astronave e superare le minacce aliene in continua evoluzione.

---

## Caratteristiche

- **Ambiente Dinamico dell'Astronave**: L'astronave è modellata come una lista collegata di stanze, ognuna con caratteristiche specifiche.
- **Gestione delle Risorse**: Cibo, munizioni e altre risorse devono essere utilizzati con attenzione per garantire la sopravvivenza.
- **Popolazione Dinamica**: Gli umani e gli Xenomorfi interagiscono in base a regole predefinite, creando una sfida sempre nuova.
- **Evoluzione degli Xenomorfi**: Gli alieni si adattano e diventano più forti, costringendo i giocatori a strategie avanzate.
- **Obiettivi Multipli**: Sopravvivere abbastanza a lungo o riparare l'astronave per attivare il sistema di fuga.

---

## Endpoints Disponibili

### Controller del Gioco (`/game`)
- **POST /game/start**: Avvia una nuova partita specificando il nome dell'astronave.
- **PUT /game/turn**: Avanza di un turno nel gioco.
- **GET /game/status**: Ottieni lo stato attuale del gioco.
- **GET /game/summary**: Ottieni un riepilogo delle azioni e degli eventi accaduti.
- **POST /game/action/explore**: Esplora l'ambiente circostante.
- **POST /game/action/fight**: Inizia un combattimento.
- **POST /game/action/repair**: Effettua riparazioni su un modulo.
- **POST /game/action/gather**: Raccogli risorse nella stanza attuale.
- **POST /game/action/reinforce**: Rafforza le difese di una stanza.
- **POST /game/end**: Termina la partita corrente.

### Controller degli Xenomorfi (`/xenomorfi`)
- **GET /xenomorfi**: Ottieni una lista di tutti gli Xenomorfi.
- **GET /xenomorfi/{id}**: Ottieni i dettagli di un Xenomorfo specifico.
- **GET /xenomorfi/stanza/{stanzaId}**: Ottieni gli Xenomorfi presenti in una stanza specifica.
- **POST /xenomorfi**: Crea un nuovo Xenomorfo.
- **PUT /xenomorfi/{id}/sposta**: Sposta un Xenomorfo in un'altra stanza.
- **PUT /xenomorfi/{id}/hp**: Aggiorna gli HP di un Xenomorfo.
- **PUT /xenomorfi/{id}/evolvi**: Evolvi un Xenomorfo.
- **DELETE /xenomorfi/{id}**: Elimina uno Xenomorfo.

### Controller degli Eventi (`/eventi`)
- **GET /eventi**: Ottieni una lista di tutti gli eventi.
- **GET /eventi/{id}**: Ottieni i dettagli di un evento specifico.
- **GET /eventi/turno/{turno}**: Ottieni gli eventi associati a un turno specifico.
- **GET /eventi/stanza/{stanzaId}**: Ottieni gli eventi in una stanza specifica.
- **POST /eventi**: Crea un nuovo evento.
- **PUT /eventi/{id}**: Aggiorna la descrizione di un evento.
- **DELETE /eventi/{id}**: Elimina un evento.

### Controller degli Umani (`/umani`)
- **GET /umani**: Ottieni una lista di tutti gli umani.
- **GET /umani/{id}**: Ottieni i dettagli di un umano specifico.
- **GET /umani/stanza/{stanzaId}**: Ottieni gli umani presenti in una stanza.
- **POST /umani**: Crea un nuovo umano.
- **PUT /umani/{id}/sposta**: Sposta un umano in un'altra stanza.
- **PUT /umani/{id}/hp**: Aggiorna gli HP di un umano.
- **DELETE /umani/{id}**: Elimina un umano.

### Controller delle Stanze (`/stanze`)
- **GET /stanze**: Ottieni una lista di tutte le stanze.
- **GET /stanze/{id}**: Ottieni i dettagli di una stanza specifica.
- **GET /stanze/tipo**: Filtra le stanze per tipo.
- **GET /stanze/barricate**: Ottieni le stanze attualmente barricate.
- **POST /stanze**: Crea una nuova stanza.
- **PUT /stanze/{id}/barricata**: Aggiorna lo stato della barricata di una stanza.
- **DELETE /stanze/{id}**: Elimina una stanza.

### Controller degli Equipaggiamenti (`/equipaggiamenti`)
- **GET /equipaggiamenti**: Ottieni una lista di tutti gli equipaggiamenti.
- **GET /equipaggiamenti/{id}**: Ottieni i dettagli di un equipaggiamento specifico.
- **GET /equipaggiamenti/stanza/{stanzaId}**: Ottieni gli equipaggiamenti in una stanza specifica.
- **POST /equipaggiamenti**: Crea un nuovo equipaggiamento.
- **PUT /equipaggiamenti/{id}/quantita**: Aggiorna la quantità di un equipaggiamento.
- **PUT /equipaggiamenti/{id}/consuma**: Consuma una quantità di un equipaggiamento.
- **DELETE /equipaggiamenti/{id}**: Elimina un equipaggiamento.

### Controller dei Moduli di Riparazione (`/moduli`)
- **GET /moduli**: Ottieni una lista di tutti i moduli.
- **GET /moduli/{id}**: Ottieni i dettagli di un modulo specifico.
- **GET /moduli/nonRiparati**: Ottieni una lista dei moduli non ancora riparati.
- **POST /moduli**: Crea un nuovo modulo di riparazione.
- **PUT /moduli/{id}/progressi**: Aggiorna i progressi di un modulo.
- **PUT /moduli/{id}/danno**: Riduci i progressi di un modulo a causa di un danno.
- **DELETE /moduli/{id}**: Elimina un modulo.

### Controller della Nave Spaziale (`/navi`)
- **GET /navi**: Ottieni una lista di tutte le navi.
- **GET /navi/{id}**: Ottieni i dettagli di una nave specifica.
- **POST /navi**: Crea una nuova nave.
- **PUT /navi/{id}/turno**: Avanza di un turno per una specifica nave.
- **DELETE /navi/{id}**: Elimina una nave.

---

## Tecnologie Utilizzate
- **Java Spring Boot**: Per lo sviluppo dei servizi REST.
- **PostgreSQL**: Database per la gestione dei dati di gioco.
- **JSON**: Formato per la comunicazione tra client e server.

---
