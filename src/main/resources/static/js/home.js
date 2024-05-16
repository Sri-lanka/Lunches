
// Función para enviar mensajes
function sendMessage() {
  // Obtener valores de los campos del formulario
  const to = document.getElementById('txtFor').value;
  const message = document.getElementById('txtMensaje').value;
  const archive = document.getElementById('txtArchive').files[0];



  // Crear un objeto para el mensaje
  const msg = {
      to: to,
      message: message,
      archive: archive,

  };

  // Agregar el mensaje al outbox
  addMessageToOutbox(msg);

  // Simular el envío del mensaje (puedes reemplazar esto con una llamada a una API)
  simulateSendMessage(msg);
}

// Función para agregar mensajes al outbox
function addMessageToOutbox(msg) {

  // Obtener la tabla del outbox
  const outboxTable = document.getElementById('outbox');

  // Crear una nueva fila para el mensaje
  const newRow = outboxTable.insertRow();

  // Crear celdas para el 'para' y el 'mensaje'
  const toCell = newRow.insertCell();
  const messageCell = newRow.insertCell();
  const archiveCell = newRow.insertCell()


  // Agregar el contenido de las celdas
  toCell.textContent = msg.to;
  messageCell.textContent = msg.message;
  archiveCell.files= msg.archive;


  // Agregar el mensaje al inbox del destinatario (simulado)
  const recipientInbox = getInboxByName(msg.to);
  recipientInbox.push(msg);
}

/*
// Función para obtener el inbox de un destinatario por su nombre
function getInboxByName(name) {
  // Simular la obtención de inboxes (puedes reemplazar esto con una llamada a una API)
  const inboxes = {
      'John': [],
      'Jane': [],
      'Mark': []
  };

  // Devolver el inbox del destinatario
  return inboxes[name];
}

// Función para simular el envío del mensaje (puedes reemplazar esto con una llamada a una API)
function simulateSendMessage(msg) {
  // Mostrar un mensaje de éxito
  alert('Message sent successfully');
}*/


function loggin(){
  let user=document.getElementById("email").value;

  let pass=document.getElementById("password").value;

  let selectElement = document.getElementById('lang');
  let type = selectElement.options[selectElement.selectedIndex].value;

  if (user == "LuisMart13@gmail.com" && pass =="1234" && type=="Apprentice"){

    window.location="home.html";

  }
  else{
    alert("DATA INCORRECT")
  }

}

function updateData() {
  // Obtener los nuevos valores ingresados por el usuario
  const newEmail = document.getElementById('newEmail').value;
  const newPhone = document.getElementById('newPhone').value;
  const newAddress = document.getElementById('newAddress').value;

  // Actualizar los valores en el HTML
  document.getElementById('emailCell').innerText = newEmail;
  document.getElementById('phoneCell').innerText = newPhone;
  document.getElementById('addressCell').innerText = newAddress;
}

function mostrarNombre() {

  var archivoInput = document.getElementById('archivo');
  var nombreMostrar = document.getElementById('nombreArchivo');

  // Obtenemos el nombre del archivo seleccionado
  var nombreArchivo = archivoInput.files[0].name;

  // Mostramos el nombre del archivo
  nombreMostrar.textContent =   nombreArchivo;
}