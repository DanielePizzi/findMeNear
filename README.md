## Introduzione

## Struttura Progetto Angular

il progetto è strutturato in modo tale da essere il più flessibile possibile. la suddivisione è la seguente:
Nella cartella web-app sono presenti le cartelle suddivise per pagina visualizzata nell'interfaccia, ogni cartella possiede al suo interno:

  * HTML
     * viene scritto l'html della pagina
  * MODULE
    * dichiara il nomer del modulo e inietta le dipendene necessarie
  
  * CONTROLLER
   *  definise le funzioni utilizzate nelle view e processa i dati ricevuto dal server(viene utilizzata la funzione $scope nativa di angular per effettuare il binding tra la view ed il controller)
  
  * SERVICE
    * si occupa della comunicazione tra la web application ed il backend
    
  * TEMPLATE COMUNE
   * nel template comune vengono definiti gli html comuni a tutta la applicazione
   * è presente anche il file templateComune.css dove viene definito tutto il foglio di stile applicato all'intera applicazione
      
## Struttura Progetto Spring+Hibernate

## Tecnologie
 * Java
 * Spring MVC
 * Angular
 * Hibernate
 * Mysql
 * Google place api
 

## Installazione
1. Scaricare l'applicazione da github
2. Importare il progetto all'interno della propria workspace di eclipse(o netbeans a seconda di cosa si usa)
3. Scaricare e installare tomcat versione 8.0 o superiore
4. Assicurarsi di far puntare le jdk di java (la versione di default di eclipse punta alle JRE nella versione per windows)
5. generare una propria api key dal seguente sito https://developers.google.com/places/web-service/get-api-key e "sostituirla" nel file script di google nell'index.html(altrimenti la mappa non viene caricata)

## Funzioni Mancanti
 * 
