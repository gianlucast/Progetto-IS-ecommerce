<!DOCTYPE html>
<html>
    <head>
        <title>About us</title>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <!-- Boostrap Core CSS-->
        <link rel="stylesheet" href="css/bootstrap.min.css"> 
        
        <!-- Main CSS -->
        <link rel="stylesheet" href="css/main.css">
        

        <!-- Google fonts -->
        <link href='http://fonts.googleapis.com/css?family=Open%20Sans:400,700,600' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Josefin+Sans:400,600,700' rel='stylesheet' type='text/css'>        
    </head>
    <body>
    
	<jsp:include page="/Header.jsp"/>
		<br><br>
      
    <div class="container">
      <div class="row padding-inner"> 
             
             <!-- Column1 -->
             <div class="col-md-6 col-sm-12">
                   
                  <h3> Cosa offriamo?</h3>
                  <p>TechZone è un sito che propone dei prodotti di ultima generazione, in un mondo in cui l'importanza della tecnologia è all'ordine del giorno e in cui si cerca di soddisfare anche i clienti con più esigenze.</p>
                  <p class="block">TechZone: because simple is awesome!</p>
                       
                 <p><span class="text">Semplicità</span> è la parola che più ci piace per descrivere il nostro e-commerce, perché vogliamo che quest'ultimo sia accessibile a chiunque.
                       Se cerchi un modo veloce e agevole di finalizzare un acquisto, scegli TechZone. Lavoriamo affinché tu possa farlo!</p>
                 
             </div>
             <!-- /column1 -->
              
             <!-- Column2 -->
             <div class="col-md-6 col-sm-12">
             <img src="img/tablet.jpeg" class="img-about">
             </div>
             <!-- /column2 -->

                   
       </div><!-- /row -->
    </div><!-- /container -->
      
      
      
    <!-- Team -->
     <div class="container team-top">
        <div class="border"></div>

    
        <h3> Chi siamo?</h3>
        <div class="row">

            <!-- Team Members -->
            <div class="col-md-4 col-sm-4 img-thumbnail-photo-top">
                <div class="thumbnail">
                    <img src="img/gerardo.png" alt="Jane Doe"/>
                        <div class="caption">
                            <h3 class="our-team-names">Gerardo Festa<br>
                               <small class="job-title">Back-end developer</small>
                            </h3>
                            <p>...</p>
                        </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 img-thumbnail-photo-top">
                <div class="thumbnail">
                    <img src="img/gianluca.png" alt="Jane Doe"/>
                        <div class="caption">
                            <h3 class="our-team-names">Gianluca Astorino<br>
                               <small class="job-title">Front-end developer</small>
                            </h3>
                            <p>...
                            </p>
                        </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 img-thumbnail-photo-top"> 
                <div class="thumbnail">
                    <img src="img/carmine.png" alt="Jane Doe"/>
                        <div class="caption">
                            <h3 class="our-team-names">Carmine Federico<br>
                               <small class="job-title">Front-end developer</small>
                            </h3>
                            <p>...
                        </div>
                </div>
            </div>
            
           </div>
           
           
           <div class="row">
           
            <div class="col-md-4 col-sm-4 img-thumbnail-photo-top"> 
                <div class="thumbnail">
                    <img src="img/emanuele.png" alt="Jane Doe"/>
                        <div class="caption">
                            <h3 class="our-team-names">Emanuele Medaglia<br>
                               <small class="job-title">Back-end developer</small>
                            </h3>
                            <p>...
                        </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4">
                   
                 <p class="block">  VUOI SAPERE DI PIU' SU DI NOI?
                 
                  Guarda la nostra sezione <a href="Info.jsp">Info e contatti</a>! 
				 </p>
				 
				 <p class="block">  VUOI LAVORARE CON NOI?
				 
                  TechZone offre anche diverse opportunità lavorative.
                  Contattaci, esplorando la sezione sovrastante, per ulteriori informazioni.
				 </p>
		   </div>
     
        </div>
        <!-- /row -->
     </div>
     <!-- /container -->

         
         <jsp:include page="/Footer.jsp"/>
         
     <script src="js/jquery-1.11.0.js"></script>

	 <script src="js/bootstrap.min.js"></script>

     <script src="js/smoothscroll.js"></script>


    </body>
    </html>
