function fetchData(url) {
  fetch(url)
    .then(response => {
      if (!response.ok) {
        throw new Error('Failed to fetch data');
      }
      return response.text();
    })
    .then(data => {
      document.getElementById('tableContainer').innerHTML = data;
      assignClickEvents();
    })
    .catch(error => {
      console.error('Error:', error);
    });
}

function assignClickEvents() {
  var activities = document.getElementById("databaseTables");

  activities.addEventListener("click", function () {
    var options = activities.querySelectorAll("option");
    var count = options.length;
  });

  activities.addEventListener("change", function () {
    if (activities.value == "User") {
      fetchData('/inicio/listUser');
    }
  });

  activities.addEventListener("change", function () {
    if (activities.value == "Assistance") {
      fetchData('/assistance/listAssistance');
    }
  });

  activities.addEventListener("change", function () {
    if (activities.value == "Authorization") {
      fetchData('/authorization/listAuthorization');
    }
  });

  activities.addEventListener("change", function () {
    if (activities.value == "Archive") {
      fetchData('/archive/listArchive');
    }
  });

  activities.addEventListener("change", function () {
    if (activities.value == "Benefit") {
      fetchData('/benefit/listBenefit');
    }
  });

  activities.addEventListener("change", function () {
    if (activities.value == "Excuse") {
      fetchData('/excuse/listExcuse');
    }
  });

  activities.addEventListener("change", function () {
    if (activities.value == "File") {
      fetchData('/file/listFile');
    }
  });

  activities.addEventListener("change", function () {
    if (activities.value == "Message") {
      fetchData('/message/listMessage');
    }
  });

  activities.addEventListener("change", function () {
    if (activities.value == "Program") {
      fetchData('/program/listProgram');
    }
  });

  activities.addEventListener("change", function () {
    if (activities.value == "User-File") {
      fetchData('/userFile/listUserFile');
    }
  });
}
assignClickEvents();