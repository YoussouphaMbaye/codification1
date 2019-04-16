<%-- 
    Document   : detailleEtudiant
    Created on : 31 juil. 2017, 20:56:27
    Author     : youssou
--%>

<%@include file="../WEB-INF/entete.jspf" %>

<section>
    <h1>Détail etudiant</h1><hr/>
    <table>
        <tr>
                   <td class="attribut">Nom:</td>
                   <td>${etudiant.nom}</td>
                   <td>&nbsp;&nbsp;&nbsp;</td>
                   <td class="attribut">Prenom:</td>
                   <td>${etudiant.prenom}</td>
        </tr>
         <tr>
                   <td class="attribut"> Code permanant: </td>
                   <td> ${etudiant.numeroEtudiant} </td>
                   <td>&nbsp;&nbsp;&nbsp;</td>
                   <td class="attribut">Date de naissance:</td>
                   <td>${etudiant.dateNai}</td>
        </tr>
         <tr>
                   <td class="attribut">Lieu:</td>
                   <td>${etudiant.lieuNai}</td>
                   <td>&nbsp;&nbsp;&nbsp;</td>
                   <td class="attribut">Sexe:</td>
                   <td>${etudiant.sexe}</td>
        </tr>
        <tr>
                   <td class="attribut">UFR:</td>
                   <td >${etudiant.ufr}</td>
                   <td>&nbsp;&nbsp;&nbsp;</td>
                   <td class="attribut">Departement:</td>
                   <td>${etudiant.departement}</td>
        </tr>
        <tr>
                   <td class="attribut">Formation:</td>
                   <td>${etudiant.formation}</td>
                   <td>&nbsp;&nbsp;&nbsp;</td>
                   <td class="attribut">Niveau:</td>
                   <td>${tabF[etudiant.niveau]}</td>
        </tr>
        <c:if test="${sessionScope.profile eq 'admine'}">
        <tr>
                   <td class="attribut">Pseudo:</td>
                   <td>${etudiant.pseudo}</td>
                   <td>&nbsp;&nbsp;&nbsp;</td>
                   <td class="attribut">Mot de passe:</td>
                   <td>${etudiant.mot_de_passe}</td>
        </tr>
        </c:if>
    </table>
</section>


<%@include file="../WEB-INF/footer.jspf" %> 
