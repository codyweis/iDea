<?php


session_start();
$sessionid = $_GET['PHPSESSID'];
	if($_SERVER['REQUEST_METHOD'] == 'GET'){
                $sqlSess = "SELECT user FROM SESSION WHERE session_id = '$sessionid'";
		require_once('connect.inc.php');

                $runSess = mysqli_query($conn, $sqlSess);

                while($sesRow = mysqli_fetch_array($runSess)){
                        $sessUser = $sesRow['user'];
                }

                $sql = "SELECT * FROM USER WHERE username =  '$sessUser'";
		$run = mysqli_query($conn, $sql);
		$result = array();
		
		while($row = mysqli_fetch_array($run)){
			array_push($result, array(
				'id' => $row['id'],
				'fname' => $row['fname'],
				'lname' => $row['lname'],
				'username' => $row['username'],
				'email' => $row['email'],
			));
		}
		
		echo json_encode(array('resultUser'=>$result));

		mysqli_close($conn);
	}
?>	