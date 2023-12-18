// app.js

function login() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `username=${username}&password=${password}`,
    })
    .then(response => {
        if (response.ok) {
            console.log('Login successful');
            // Redirect to the survey page after successful login
            window.location.href = '/survey.html';
        } else {
            console.error('Login failed');
            // Handle login failure (e.g., show error message)
        }
    })
    .catch(error => {
        console.error('Error during login:', error);
    });
}

function submitSurvey() {
    const surveyUsername = document.getElementById("surveyUsername").value;
    const answers = document.getElementById("answers").value;
    const fileInput = document.getElementById("file");
    const file = fileInput.files[0];

    const formData = new FormData();
    formData.append('username', surveyUsername);
    formData.append('answers', answers);
    formData.append('file', file);

    fetch('/survey', {
        method: 'POST',
        body: formData,
    })
    .then(response => {
        if (response.ok) {
            console.log('Survey submitted successfully');
            // Handle successful survey submission (e.g., show success message)
        } else {
            console.error('Survey submission failed');
            // Handle survey submission failure (e.g., show error message)
        }
    })
    .catch(error => {
        console.error('Error during survey submission:', error);
    });
}
