<%-- 
    Document   : contact
    Created on : 5 juin 2017, 00:08:41
    Author     : youssou
--%>

<%@include file="../WEB-INF/entete.jspf" %>
<section>
    <h1>Contact</h1><hr/>
    <div class="slider">
        <div class="slide"><img src="img/tslide2.jpg" alt="slide2" width="560px"/></div>
    </div>
    <div class="contenueAccueil">
        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3859.294773711918!2d-16.480581301013046!3d14.695915078674856!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0xeea09719f211b75%3A0x90dc153ff6216750!2sUniversit%C3%A9+Alioune+DIOP+de+Bambey!5e0!3m2!1sfr!2sfr!4v1496017631240" width="557" height="350" frameborder="0" style="border:0" allowfullscreen></iframe>
        <div class="contact-form">
            <p>
            <h2>Information</h2>
            Nos services sont ouverts du lundi au vendredi de 8h30mn à  17h30mn pour toute 
            information supplémentaire nous contacter au  +221 70 637 30 51 ou nous écrire 
            par email à l'adresse suivante codificationuadb@gmail.com
            </p>
            <h2>Formulaire de contact</h2>
            <form action="Controleur" method="POST">
                <input type="email" name="email" placeholder="email" size="52" id="email"required/><br/><br/>
                <input type="text" placeholder="Sujet" name="sujet" size="52" required><br/><br/>
                <textarea name="message" required type="text" rows="10" cols="40">Message...</textarea><br/><br/>
                <input type="hidden" name="action" value="envoyerLeMail"/>
                <input type="submit" value="Envoyer" onclick='Javascript:checkEmail();' >
            </form>
        </div> 
    </div>

<c:if test="${!empty reussi}">
        <p class="reussi">${reussi}</p>
    </c:if>
    <c:if test="${!empty erreure}">
        <p class="erreur">${erreure}</p>
    </c:if>
</section>


<%@include file="../WEB-INF/footer.jspf" %> 