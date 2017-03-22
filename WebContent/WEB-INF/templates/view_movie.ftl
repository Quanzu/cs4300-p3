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
				<#if success??>
					<#if success == "Movie could not be edited due to empty form submission.">
						<p class="red">${success}</p>
					<#elseif success == "Movie has been successfully edited!">
						<p class="green">${success}</p>
					</#if>
				</#if>
				<h2>Information</h2>
				<p>
				Rating: ${movie.rank} <br/>
				Release Year: ${movie.year} <br/>
				Genre: ${movie.genre} <br/>
				</p>
				
				<form class=center action="ReviewServlet" method=post>
					<input type=hidden name="movieId" value="${movie.id}" />
					<input type=submit name="editMovie" value="Edit Movie" />
					<input type=submit name="deleteMovie" value="Delete Movie" />
				</form>
				
				<hr>
				
				<div id=reviews>
					<h2>Reviews</h2>
					
					<#if success??>
						<#if success == "Review was successfully added!">
							<p class="green">${success}</p>
						<#elseif success == "Sorry, review was not added due to empty form submission. Please try again.">
							<p class="red">${success}</p>
						</#if>
					</#if>
					
					<#if reviews??>
						<form action="ReviewServlet" method=post>
							<input type=hidden name="movieId" value="${movie.id}" />
								<table>
									<tbody>
										<#list reviews as review>
											<tr><td><p>"${review.content}"</p></td><td><input type=checkbox name="${review.id}" /></td></tr>
										</#list>
										<tr><td><input type=submit name="deleteReviews" value = "Delete Selected Reviews" /></td></tr>
									</tbody>
							</table>
						</form>
						<br/>
					<#else>
						<p>No reviews yet. Feel free to leave your own.</p>
					</#if>
					
					<table>
					<tr><td><textarea name="reviewContent" placeholder="What do you think about ${movie.name}?" form="review" rows=5 cols=50 wrap="hard"></textarea></td></tr>
					<form action="ReviewServlet" method=post id=review>
						<input type=hidden name="movieId" value="${movie.id}" />
						<tr><td><input type=submit name="newReview" value="Create Review" /></td></tr>
					</form>
					</table>
				</div>
		   	</div>
        </body>
        
    </html>