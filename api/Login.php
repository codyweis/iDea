<?php
session_start();
session_regenerate_id();
$sessionid = session_id();
if($_SERVER['REQUEST_METHOD'] == 'POST'){
		require_once('connect.inc.php');
		 
		$sql = "SELECT username, password FROM USER WHERE username = ?";
		
		$stmt = $conn->prepare($sql);

		$username = $_POST["username"];
		$password = $_POST["password"];

                $sqlSess = "INSERT INTO SESSION(session_id, user) VALUES('$sessionid', '$username')";

		$stmt->bind_param("s", $username);

		$stmt->execute();
		
		$stmt->bind_result($user, $pass);

		while($stmt->fetch()){
			$verify = password_verify($password, $pass);
		}

		if($verify){
                        if(mysqli_query($conn, $sqlSess)){
			        echo 'connected';
echo $sessionid;
                        }
		}else{
			echo 'check details';
		}


		mysqli_close($conn);
	}
?>