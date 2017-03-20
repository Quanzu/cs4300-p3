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
                    <input type=text name=searchForMovie placeholder="Movie title" />
                    <input type=submit name=searchForMovie value = "Search" />
                </form>
				<h1>Viewing: "${movie.name}"</h1>
				<hr>
			</div>  
			<div id = content>
				Rating: ${movie.rank} <br/>
				Release Year: ${movie.year} <br/>
				Genre: ${movie.genre} <br/>
		   	</div>
        </body>
        
    </html>