<%-- 
    Document   : connexion
    Created on : 1 juin 2017, 08:47:21
    Author     : youssou
--%>

<%@include file="../WEB-INF/entete.jspf" %>
       <section>
          <h1 align="center">Connectez-vous pour plus de <span>fonctionnalités</span></h1>
        <form action="Controleur" method="POST">
             <center> <p class="erreurAuth">${erreurAuth}${param.erreurAuth}</p></center>
            <center> 
                <table width="30%" height="40%">
                <tr><th bgcolor="blue"><p  style="color:white;">AUTHENTIFICATION</p></th></tr>
                <tr bgcolor="Grey">
                    <td>
                   
                        <label for="pseudo" class="pseudo">Pseudo</label><br>
                        <input type="text" class="input1" name="pseudo" id="pseudo" size="26" value="${param.pseudo}" required/><br><br>
                   
                        <label for="motDePasse" class="passwd">Mot de passe</label><br>
                        <input type="password" class="input2" name="motDePasse"  size="26" id="motDePasse" required/><br><br>
                    
                        <label for="Profil" class="profile">Profile</label><br>
                        <select class="select" id="profile" name="profil" style="width: 302px;">
                            <option value="Etudiant">Etudiant</option>
                            <option value="Secretaire">Secretaire</option>
                            <option value="Comptable">Comptable</option>
                            <option value="Chef de residence">Chef de residence</option>
                            <option value="admine">Administrateur</option>
                        </select>
                        <br><P><b style="color: red;">${message}</b></P>
                    </td>
                </tr>
                <tr height="60" bgcolor="blue" height="10%">
                    
                    <td align="right" >
                        <input class="submit" type="submit" name="action" value="Se connecter"/>

                    </td>
                </tr>
            </table>
            </center>
            
            
        </form>
                       
  </section>


<%@include file="../WEB-INF/footer.jspf" %> 
