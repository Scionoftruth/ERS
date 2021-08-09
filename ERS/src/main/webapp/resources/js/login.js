/**
 * 
 */
 let form = document.getElementById('login').addEventListener('submit', login);
 
 
 async function login(e){
	e.preventDefault();
	let username = document.getElementById('username').value;
	let password = document.getElementById('password').value;
	
	let user = {
		username,
		password
	};
	
	console.log(user);
	
	try{
		let req = await fetch('http://localhost:8080/ERS/api/login',{
			method: 'POST',
			headers:{
				'Content-Type' : 'application/json'
			},
			body: JSON.stringify(user)
		});
		let res = await req.json();
		console.log(res);
		if(res.role==='EMPLOYEE'){
			location.href = 'resources/html/employee-dashboard.html';
		}else{
			location.href = 'resources/html/manager-dashboard.html';
		}

	} catch(e){
		alert("Username or Password was Incorrect");
		return;	
	}
}