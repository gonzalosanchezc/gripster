<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="Images/company/1.png">

        <title>Verificar Código | Gripster</title>

        <!-- Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700&family=Space+Grotesk:wght@400;500;600&display=swap" rel="stylesheet">

        <!-- Styles -->
        <link rel="stylesheet" href="Styles/general/navbar-white.css">
        <link rel="stylesheet" href="Styles/general/allStyles.css">
        <link rel="stylesheet" href="Styles/login-styles/verific-styles-1.css">

        <!-- Bootstrap Icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    </head>
    <body class="verify-code-page">
        <!-- Video de fondo (consistente con login) -->
        <div class="video-background">
            <video id="background-video" muted loop>
                <source src="Images/company/background1.mp4" type="video/mp4">
            </video>
            <div class="video-overlay"></div>
        </div>

        <!-- Contenido principal -->
        <main class="verify-container">
            <div class="verify-brand">
                <img src="Images/company/1.svg" alt="Logo de Gripster" class="logo">
                <h1>Gripster</h1>
                <p class="brand-slogan">Tu futuro comienza aquí</p>
            </div>

            <div class="verify-card">
                <div class="verify-header">
                    <h2 class="verify-title">Verifica tu código</h2>
                    <p class="verify-subtitle">Hemos enviado un código de 6 dígitos a <strong><%= request.getAttribute("correo") != null ? request.getAttribute("correo") : ""%></strong></p>
                </div>

                <form action="SvVerificarCodigo" method="post" class="verify-form">
                    <input type="hidden" name="correo" value="<%= request.getAttribute("correo") != null ? request.getAttribute("correo") : ""%>">

                    <div class="form-group">
                        <label for="codigo" class="form-label">Código de acceso</label>
                        <div class="input-group code-input">
                            <span class="input-icon">
                                <i class="bi bi-shield-lock"></i>
                            </span>
                            <input type="text" name="codigo" id="codigo" class="form-input" maxlength="6" required placeholder="••••••">
                        </div>
                    </div>

                    <button type="submit" class="verify-button">
                        <span>Verificar código</span>
                        <i class="bi bi-arrow-right"></i>
                    </button>
                </form>

                <% if (request.getAttribute("mensaje") != null) {%>
                <div class="success-message">
                    <i class="bi bi-check-circle"></i>
                    <span><%= request.getAttribute("mensaje")%></span>
                </div>
                <% } %>

                <% if (request.getAttribute("error") != null) {%>
                <div class="error-message">
                    <i class="bi bi-exclamation-circle"></i>
                    <span><%= request.getAttribute("error")%></span>
                </div>
                <% }%>

                <div class="resend-code">
                    <p>¿No recibiste el código? <a href="login.jsp" class="resend-link">Reenviar código</a></p>
                </div>
            </div>
        </main>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const video = document.getElementById("background-video");
                video.setAttribute("autoplay", "true");
                video.play().catch(e => console.log("Autoplay prevented:", e));

                // Auto-focus en el input del código
                document.getElementById("codigo").focus();

                // Auto-avance entre inputs (simulado para código de 6 dígitos)
                const codeInput = document.getElementById("codigo");
                codeInput.addEventListener('input', function (e) {
                    if (this.value.length === 6) {
                        this.blur();
                        document.querySelector(".verify-button").focus();
                    }
                });
            });
        </script>
    </body>
</html>