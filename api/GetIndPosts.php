<?php
session_start();
$sessionid = $_GET['PHPSESSID'];
	if($_SERVER['REQUEST_METHOD'] == 'GET'){
	        $usersess = "SELECT user FROM SESSION WHERE session_id = '$sessionid'";
		require_once('connect.inc.php');

                $runsess = mysqli_query($conn, $usersess);

                while($sesRow = mysqli_fetch_array($runsess)){
                        $username = $sesRow['user'];
                }

                $sql = "SELECT * FROM IDEA WHERE user = '$username' ORDER BY post_date ASC";
		$run = mysqli_query($conn, $sql);

		$result = array();
		
		while($row = mysqli_fetch_array($run)){
			array_push($result, array(
				'id' => $row['id'],
				'user' => $row['user'],
				'topic_title' => $row['topic_title'],
				'content' => $row['content'],
				'post_date' => $row['post_date'],
                                'likes' => $row['likes']
			));
		}
		
		echo json_encode(array('resultIndPost'=>$result));
		
		mysqli_close($conn);
	}
?>