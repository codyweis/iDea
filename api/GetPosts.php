<?php

	if($_SERVER['REQUEST_METHOD'] == 'GET'){
	
		require_once('connect.inc.php');
		
		$sql = "SELECT * FROM IDEA ORDER BY post_date ASC";
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
		
		echo json_encode(array('resultPost'=>$result));
		
		mysqli_close($conn);
	}
?>