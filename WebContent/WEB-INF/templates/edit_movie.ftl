<!DOCTYPE html>
    <html>

        <head>
            <link href="https://fonts.googleapis.com/css?family=Oswald" rel="stylesheet">
            <link href="css/index.css" type="text/css" rel=stylesheet />
            <title>Review Hub</title>
        </head>
        
        <body>
        <div id=wrapper>
            <div id=header>
                <form action="ReviewServlet" method=post>
                	<input type=submit name=home value="Home" /><!--
                    --><input type=submit name=viewAll value="View All"/><!--
                    --><input type=submit name=viewGenre value="View All In Genre"/><!--  
                    --><input type=submit name=newMovie value="Submit A New Movie"/>
                    <input type=text name=searchForTitle placeholder="Movie title" />
                    <input type=submit name=search value = "Search" />
                </form>
                <h1>Editing Movie: "${movie.name}"</h1>
                <hr>
            </div>
            <div id=content>
            
            <#if success??>
            	<#if success == "Movie was successfully edited!">
            		<p class="center green">${success}</p>
            	<#else>
            		<p class="center red">${success}</p>
            	</#if>
            </#if>
            <form action="ReviewServlet" method = post>
         		<input type=hidden name="movieId" value="${movie.id}" />
                <table>
                    <tbody>
                        <tr><td>Title:</td><td><input type="text" name="title" value="${movie.name}" /></td></tr>            		
                        <tr><td>Year:</td><td><input type="number" name="year" min=0 value="${movie.year}"/></td></tr>
                        <tr><td>Rank:</td><td><input type="number" name="rank" min=0 max=10 step=.1 value="${movie.rank}"/></td></tr>
                        <tr><td>Genre:</td><td><input type="text" name="genre" value="${movie.genre}" /></td></tr>
                        <tr><td></td><td><input type=submit name="updateMovie" value="Update Movie" /></td></tr>
                    </tbody>
                </table>
                <br/>
            </form>
            </div>
        </div>    
        </body>
        
    </html>