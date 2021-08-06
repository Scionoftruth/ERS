let userId;


let verifyLoggedIn = async() => {
	let res = await fetch('http://localhost:8080/ERS/api/getSession');
	let obj = await res.json();
	console.log(obj);

	if (obj.userid < 0) {
		location.href = "../html/index.html";
	}
	else {
		userId = obj.userid;
	}
}

document.getElementById("detailed").addEventListener('click', () => {
	location.href = "../html/employee-detailed.html";
});

document.getElementById("logout").addEventListener('click', async () => {
	let res = await fetch('http://localhost:8080/ERS/api/logout');
	userId = -1;
	verifyLoggedIn();
});

/*
When the Submit New reimbursement button is clicked, it will either
hide of show the form
*/
document.getElementById("new-ticket").addEventListener('click', () => {
	if (document.getElementById("new-reimbursement").style.display === "flex") {
		document.getElementById("new-reimbursement").style.display = "none";
	} else{
		document.getElementById("new-reimbursement").style.display = "flex";
	}
});

/* Fetching Reimbursements and Dom manipulation functions */

let retreiveAllReimbursements = async () => {
	let res = await fetch(`http://localhost:8080/ERS/api/getAllById?id=${userId}`);
	let obj = await res.json();

	console.log(obj);
	return obj;
}

let populateTable = (objList) => {

	let table = document.getElementById("re-table");

	table.innerHTML = '<tr><th>REIMBURSEMENT STATUS</th><th>REIMBURSEMENT TYPE</th><th>REIMBURSEMENT AMOUNT</th><th>DATE OF SUBMISSION</th><th>DATE OF RESOLUTION</th><th>REIMBURSEMENT RESOLVER</th></tr>';


	objList.forEach((obj) =>{
		let index = 1;
		let row=table.insertRow(index++);
		row.id = obj.reId;

		let status = row.insertCell(0);
		status.innerHTML = obj.status.reimbursement_status;
		let type = row.insertCell(1);
		type.innerHTML = obj.type.reimbursement_type;
		let amount = row.insertCell(2);
		amount.innerHTML = Number (obj.amount).toFixed(2);
		let subDate = row.insertCell(3);
		subDate.innerHTML = new Date(obj.submitteddate).toDateString();
		let resDate = row.insertCell(4);

		if(obj.resolveddate !== null){
		resDate.innerHTML = new Date(obj.resolveddate).toDateString();
		}
		else {
		resDate.innerHTML = 'N/A';
		}

		let resolver = row.insertCell(5);
		if (obj.manager !== null) {
			resolver.innerHTML = obj.manager.username;
		}
		else {
			resolver.innerHTML = 'N/A';
		}
	});
}


let submitTicket = async (e) => {
	e.preventDefault();
	

	let amount = document.getElementById("amount").value;
	let date = document.getElementById("date").value;
	let type = document.getElementById("types").value;
	let desc = document.getElementById("desc").value;
	
	if (!amount || !date || !desc) {
		alert("Please fill the empty fields");
		document.getElementById("re-form").reset();
		return;
	}
	else if(amount<=0){
		alert("Negative amount doesn't make sense, please enter positive amount");
		document.getElementById("re-form").reset();
		return;
	}
	else {
		let d = Date.parse(date);
		console.log(d);
		let obj = {
			amount: amount,
			date: Date.parse(date),
			type: type,
			desc: desc,
			author: userId
		};

		let req = await fetch('http://localhost:8080/ERS/api/newreimbursement', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(obj)
		});
		
		let res = await req.json();
		
		document.getElementById("re-form").reset();
		document.getElementById("new-reimbursement").style.display = "none";
		
		let reimbursements = await retreiveAllReimbursements();
		populateTable(reimbursements);
	}
}

document.getElementById("send").addEventListener('click', submitTicket);

/* Make all the calls needed to populate the page */


let init = async () => {
	await verifyLoggedIn();
	let res = await fetch(`http://localhost:8080/ERS/api/getUser?userid=${userId}`);
	let user = await res.json();
	console.log(user);
	let userName = user.username;
	console.log(userName);
	document.getElementById("welcome").innerText = `Welcome ${userName}!`;
	document.getElementById("re-name").innerText = `${userName}'s Past Reimbursements`;
	let reimbursements = await retreiveAllReimbursements();
	populateTable(reimbursements);
}

init();