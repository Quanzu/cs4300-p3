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
                <h1>The Review Hub</h1>
                <hr>
            </div>
            <div id=content>
            
            <#if success??>
            	<#if success == "Movie was successfully added!">
            		<p class="center green">${success}</p>
            	<#else>
            		<p class="center red">${success}</p>
            	</#if>
            </#if>
            <form action="ReviewServlet" method = post>
                <table>
                    <tbody>
                        <tr><td>Title:</td><td><input type="text" name="title" /></td></tr>            		
                        <tr><td>Year:</td><td><input type="number" name="year" min=0 /></td></tr>
                        <tr><td>Rank:</td><td><input type="number" name="rank" min=0 max=10 step=.1 /></td></tr>
                        <tr><td>Genre:</td><td><input type="text" name="genre" /></td></tr>
                        <tr><td></td><td><input type=submit name="addNewMovie" value="Create Movie" /></td></tr>
                    </tbody>
                </table>
                <br/>

            </form>
            </div>
        </div>    
        </body>
        
    </html>