<%-- 
    Document   : accueil
    Created on : 25 mai 2017, 15:49:51
    Author     : youssou
--%>

    <%@include file="../WEB-INF/entete.jspf" %>
            <section>
                <h1>Accueil</h1>
                <hr/>
                <div class="slider">
                    <div class="slides">
                        <div class="slide"><img src="img/tslide1.jpg" alt="slide1" width="560px"/></div>
                        <div class="slide"><img src="img/tslide2.jpg" alt="slide2" width="560px"/></div>
                        <div class="slide"><img src="img/tslide3.jpg" alt="slide3" width="560px"/></div>
                    </div>
                </div>
                <div class="contenueAccueil">
                   <div class="aDroite"> 
                       <p class="horloge"><%@include file="../WEB-INF/leTemps.jspf" %></p>
                   <img src="img/bat1.jpg" alt="drec" /> 
                   
                   </div>
                   <div class="aGauche"> 
                   <p>
                       <img src="img/drec.jpg" alt="drec" class="imDect"/>  
                   <h3>&nbsp;&nbsp;&nbsp;&nbsp;Le mot du directeur</h3><br/><br/>
                   &nbsp;&nbsp;&nbsp;&nbsp;Bienvenue au campus social<br/><br/>
Admis aux b�n�fices des oeuvres, profitez des services offerts aux �tudiants par le COUD. 
Ces services concernent le logement, la restauration, 
le m�dical, les aides sociales (bourses, dons d'urgence) ainsi que les actions sportives et socioculturelles. 
Le campus social est un lieu de vie collective, avec ses exigences en mati�re de s�curit�, de confort et de travail. 
Tous ces �l�ments sont pr�cis�s dans le r�glement int�rieur du COUD.
Nos pr�occupations principales sont entre autres, d'am�liorer vos conditions de vie, d'�tudes et de r�pondre � vos attentes.
Je vous souhaite une excellente ann�e universitaire au campus social. Que celle-ci soit riche tant en succ�s dans vos �tudes qu'en exp�riences de la vie �tudiante !

</p>
                   </div>
                </div>

            </section>

      <%@include file="../WEB-INF/footer.jspf" %>         