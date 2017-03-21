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
				<h1>Viewing: "${movie.name}"</h1>
				<hr>
			</div>  
			<div id= content class=padding>
				<h2>Information</h2>
				<p>
				Rating: ${movie.rank} <br/>
				Release Year: ${movie.year} <br/>
				Genre: ${movie.genre} <br/>
				</p>
				
				<form class=center action="ReviewServlet" method=post>
					<input type=submit name="editMovie" value="Edit Movie" />
				</form>
				
				<hr>
				<div id=reviews>
					<h2>Reviews</h2>
					
					<#if success??>
						<#if success == "Review was successfully added!">
							<p class="green">${success}</p>
						<#else>
							<p class="red">${success}</p>
						</#if>
					</#if>
				
					<#if reviews??>
						<#list reviews as reviews>
						<p>"${reviews.content}"</p>
						</#list>
					<#else>
						<p>No reviews yet. Feel free to leave your own.</p>
					</#if>
					
					<table>
					<tr><td><textarea name="reviewContent" placeholder="What do you think about ${movie.name}?" form="review" rows=5 cols=50></textarea></td></tr>
					<form action="ReviewServlet" method=post id=review>
						<input type=hidden name="movieId" value="${movie.id}" />
						<tr><td><input type=submit name="newReview" value="Create Review" /></td></tr>
					</form>
					</table>
				</div>
		   	</div>
        </body>
        
    </html>