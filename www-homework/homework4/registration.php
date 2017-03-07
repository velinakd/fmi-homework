<!DOCTYPE HTML>
<html>
    <head>
    </head>
    <body>
        <form method="post" action="">
            <h1>REGISTRATION</h1>
                 <?php
            $names = array("first_name" => "First name", "last_name" => "Last name",
                "email" => "Email", "sex" => "Sex", "birthdate" =>"Date of Birth",
                "password" => "Password", "password_again" => "Password again",
                "movietype" => "Favorite type of movies", "movies_from" => "Movies from",
                "favorite_actors" => "Favorite actors", "movie_person" => "Favorite movie person",
                "more_about" => "More about you");
            if(!empty($_POST)) {
                foreach ($names as $field => $field_label) {
                    if(empty($_POST[$field])) {
                        echo "Please enter a value for ".$names[$field];?><br><?php
                    }
                }
            }
        ?>
            <h2>Personal details</h2>
            <label for="first_name">First name</label>
            <input type="text" name="first_name" id="first_name">
            <label for="last_name">Last name</label>
            <input type="text" name="last_name" id="last_name">
            <label for="email">Email</label>
            <input type="email" name="email" id="email"><br>
            <label for="sex" id="sex">Sex</label>
            <input type="radio" name="sex" id="male" value="male">
            <label for="male">Male</label>
            <input type="radio" name="sex" id="female" value="female">
            <label for="female">Female</label><br>
            <label for="birthdate">Date of birth</label>
            <input type="date" name="birthdate" id="birthdate"><br>
            <label for="password">Password</label>
            <input type="password" name="password">
            <label for="password_again">Password again</label>
            <input type="password" name="password_again">
            <h2>More about yourself</h2>
            <label for="movietype">Favorite type of movies</label>
            <select name="movietype">
                <option value="drama">Drama</option>
                <option value="comedy">Comedy</option>
                <option value="romance">Romance</option>
                <option value="thriller">Thriller</option>
                <option value="horror">Horror</option>
            </select><br>
            <label for="movies_from">Movies from</label>
            <input type="checkbox" name="movies_from" id="usa" value="usa">
            <label for="usa">USA</label>
            <input type="checkbox" name="movies_from" id="europe" value="europe">
            <label for="europe">Europe</label>
            <input type="checkbox" name="movies_from" id="asia" value="asia">
            <label for="asia">Asia</label><br>
            <label for="favorite_actors">Favorite actors</label>
            <input type="text" name="favorite_actors" id="favorite_actors">
            <label for="movie_person">Favorite movie person</label>
            <input type="text" name="movie_person" id="movie_person">
            <label for="more_about">More about you</label>
            <textarea name="more_about" id="more_about" rows="2" cols="10"></textarea><br>
            <input type="submit" value="Register me!"><br>
        </form>
    </body>
</html>