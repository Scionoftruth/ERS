/* User verification and logout functions / variables */

let userid;

let verifyLoggedIn = async () => {
	let res = await fetch('http://localhost:8080/ERS/api/getSession');
	let obj = await res.json();
	console.log(obj);

	if (obj.userid < 0) {
		location.href = "resources/html/index.html";
	}
	else {
		userId = obj.userid;
	}
}

document.getElementById("logout").addEventListener('click', async () => {
	let res = await fetch('http://localhost:8080/ERS/api/logout');
	location.href = "http://localhost:8080/ERS/";
});

document.getElementById("home").addEventListener('click', () => {
	location.href = "../html/employee-dashboard.html";
});

/* Reimbursement functions */

let populateTable = (obj) => {

	let table = document.getElementById("re-table");

	table.innerHTML = '<tr><th>Ticket Id</th><th>Status</th><th>Type</th><th>Amount</th><th>Submitted Date</th><th>Resolved Date</th><th>Resolved By</th></tr>';

	obj.forEach((obj) => {
		let index = 1;

		let row = table.insertRow(index++);
		row.id = obj.id;

		let ticketId = row.insertCell(0);
		ticketId.innerHTML = obj.id;

		let status = row.insertCell(1);
		status.innerHTML = obj.status.reimbursement_status;

		let type = row.insertCell(2);
		type.innerHTML = obj.type.reimbursement_type;

		let amount = row.insertCell(3);
		amount.innerHTML = Number(obj.amount).toFixed(2);

		let subDate = row.insertCell(4);
		subDate.innerHTML = new Date(obj.submitteddate).toDateString();

		let resDate = row.insertCell(5);
		if (obj.resolveddate !== null) {
			resDate.innerHTML = new Date(obj.resolveddate).toDateString();
		}
		else {
			resDate.innerHTML = 'N/A';
		}

		let resolver = row.insertCell(6);
		if (obj.manager !== null) {
			resolver.innerHTML = obj.manager.username;
		}
		else {
			resolver.innerHTML = 'N/A';
		}

		let descRow = table.insertRow(index++);
		let desc = descRow.insertCell(0);
		desc.setAttribute("colspan", "7");
		desc.className = "desc";
		desc.innerHTML = `<h3>Description:</h3><p>${obj.description}</p>`;

	});
}

document.getElementById("filter").addEventListener('click', async () => {
	let status = document.getElementById("status").value;
	console.log(status);
	if(status<3){
		let res = await fetch(`http://localhost:8080/ERS/api/getAllById?id=${userId}`);
		let obj = await res.json();
		console.log(obj);
		if(status == 0){
			const result = obj.filter(reimb => reimb.status.reimbursement_status === 'PENDING');
			console.log(result);
			populateTable(result);
		}else if(status == 1){
			const result = obj.filter(reimb => reimb.status.reimbursement_status === 'APPROVED');
			console.log(result);
			populateTable(result);
		}
		else{
			const result = obj.filter(reimb => reimb.status.reimbursement_status === 'DENIED');
			console.log(result);
			populateTable(result);
		}
	}
	else{
		let obj = await retreiveAllReimbursements();
		populateTable(obj);
	}
});

let retreiveAllReimbursements = async () => {
	let res = await fetch(`http://localhost:8080/ERS/api/getAllById?id=${userId}`);
	let obj = await res.json();
	console.log(obj);
	return obj;
}

/*After login initalize the table */

let init = async () => {
	await verifyLoggedIn();
	let res = await fetch(`http://localhost:8080/ERS/api/getUser?userid=${userId}`);
	let user = await res.json();
	console.log(user);
	let userName = user.username;
	document.getElementById("welcome").innerText = `Welcome ${userName}!`;
	document.getElementById("re-name").innerText = `${userName}'s Reimbursements`;
	let rows = await retreiveAllReimbursements();
	populateTable(rows);
}

init();