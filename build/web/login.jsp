<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="Images/company/1.png">

        <title>Gripster: Estamos iniciandio... | Gripster</title>

        <!-- Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700&family=Space+Grotesk:wght@400;500;600&display=swap" rel="stylesheet">

        <!-- Styles -->
        <link rel="stylesheet" href="Styles/general/navbar-white.css">
        <link rel="stylesheet" href="Styles/general/allStyles.css"/>
        <link rel="stylesheet" href="Styles/login-styles/main-login-first.css"/>

        <!-- Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css">

        <!-- Bootstrap Icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet" />
    </head>
    

    <body class="login-page">
        <!-- Video de fondo -->
        <div class="video-background">
            <video id="background-video" muted loop>
                <source src="Images/company/background1.mp4" type="video/mp4">
            </video>
            <div class="video-overlay"></div>
        </div>

        <!-- Contenido principal -->
        <main class="login-container">
            <div class="login-brand">
                <img src="Images/company/1.svg" alt="Logo de Gripster" class="logo">
                <h1>Gripster</h1>
                <p class="brand-slogan">Tu futuro comienza aquí</p>
            </div>

            <div class="login-card">
                <h2 class="login-title">Iniciar Sesión</h2>
                <p class="login-subtitle">Ingresa tu correo con el que compraste para recibir un código de acceso</p>

                <form action="SvGenerarCodigo" method="post" class="login-form">
                    <div class="form-group">
                        <label for="correo" class="form-label">Correo Electrónico</label>
                        <div class="input-group">
                            <span class="input-icon">
                                <i class="bi bi-envelope"></i>
                            </span>
                            <input type="email" name="correo" id="correo" class="form-input" required placeholder="tucorreo@ejemplo.com">
                        </div>
                    </div>

                    <button type="submit" class="login-button">
                        <span>Enviar código de acceso</span>
                        <i class="bi bi-arrow-right"></i>
                    </button>
                </form>

                <% if (request.getAttribute("error") != null) {%>
                <div class="error-message">
                    <i class="bi bi-exclamation-circle"></i>
                    <span><%= request.getAttribute("error")%></span>
                </div>
                <% }%>
            </div>

            <div class="login-footer">
                <p>¿No tienes una cuenta? Es porque no has comprado )': <a href="index.html" class="footer-link">Puedes hacerlo aquí</a></p>
            </div>
        </main>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const video = document.getElementById("background-video");
                video.setAttribute("autoplay", "true");
                video.play().catch(e => console.log("Autoplay prevented:", e));
            });
        </script>
    </body>
</html>