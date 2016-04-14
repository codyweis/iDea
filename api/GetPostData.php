<?php

	if($_SERVER['REQUEST_METHOD'] == 'GET'){
                $sqlSess = "SELECT id,user FROM IDEA ORDER BY id DESC";
		require_once('connect.inc.php');

                $runSess = mysqli_query($conn, $sqlSess);

		$result = array();
		
		while($row = mysqli_fetch_array($runSess)){
			array_push($result, array(
				'id' => $row['id'],
				'user' => $row['user']
				
			));
		}
		
		echo json_encode(array('dataResult'=>$result));

		mysqli_close($conn);
	}
?>	