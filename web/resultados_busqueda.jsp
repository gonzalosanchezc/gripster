<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.gripster.model.Llanta, java.util.List" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <link rel="icon" href="Images/company/1.png">
        <%
            Integer width = (Integer) request.getAttribute("width");
            Integer profile = (Integer) request.getAttribute("profile");
            Integer diameter = (Integer) request.getAttribute("diameter");

            String title = "Medida de llanta: Lo mejor para tu coche";
            if (width != null && profile != null && diameter != null) {
                title = width + "/" + profile + "R" + diameter + " | " + title;
            }
        %>
        <title><%= title%></title>

        <!-- Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link
            href="https://fonts.googleapis.com/css2?family=Creepster&family=Montserrat:ital,wght@0,100..900;1,100..900&family=Rubik+Wet+Paint&family=Source+Sans+3:ital,wght@0,200..900;1,200..900&family=Space+Grotesk:wght@300..700&display=swap"
            rel="stylesheet">

        <!-- Styles -->
        <link rel="stylesheet" href="Styles/general/navbar-white.css">
        <link rel="stylesheet" href="Styles/general/allStyles.css">
        <link rel="stylesheet" href="Styles/results/results-sections-main.css"/>

        <!-- Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg fixed-top">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.html">
                    <img src="Images/company/1.svg" alt="Logo de Gripster" width="30" height="30"
                         class="d-inline-block align-text-top">
                    G&nbsp;R&nbsp;I&nbsp;P&nbsp;S&nbsp;T&nbsp;E&nbsp;R
                </a>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item ms-3">
                            <a class="nav-link underline-link" id="consejos-link" href="index.html">
                                <i class="bi bi-house"></i> Inicio
                            </a>
                        </li>
                    </ul>

                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link special-link" id="cart-icon" href="login.jsp">
                                <i class="bi bi-person"></i>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link special-link" id="cart-icon" href="SvCarrito">
                                <i class="bi bi-cart4"></i>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <section class="results-section-cards">
            <h2 id="txt-search">Resultados para <%= width + "/" + profile + "R" + diameter%></h2>

            <%
                List<Llanta> tires = (List<Llanta>) request.getAttribute("tires");
                if (tires != null && !tires.isEmpty()) {
                    for (Llanta tire : tires) {
            %>
            <div class="control-cards-tires">
                <div class="cards-tires-results">
                    <article class="left-card-side">
                        <img src="<%= tire.getImagen()%>" alt="<%= tire.getModeloNombre()%>" class="tire-crop">
                        <h3><%= tire.getModeloNombre()%></h3>
                    </article>
                    <article class="right-card-side">
                        <p><strong>Marca:</strong> <%= tire.getMarcaNombre()%></p>
                        <p><strong>Medida:</strong> <%= tire.getAncho()%>/<%= tire.getPerfil()%>R<%= tire.getDiametro()%></p>
                        <p><strong>Precio:</strong> $<%= String.format("%,.2f", tire.getPrecio())%> MXN</p>
                        <p><strong>Disponibilidad:</strong> <%= tire.getStock() > 0 ? "EN STOCK" : "AGOTADO"%></p>

                        <form action="SvCarrito" method="POST">
                            <input type="hidden" name="action" value="add">
                            <input type="hidden" name="tireId" value="<%= tire.getIdLlanta()%>">
                            <input type="hidden" name="quantity" value="1">
                            <button type="submit" <%= tire.getStock() <= 0 ? "disabled" : ""%>>
                                <%= tire.getStock() > 0 ? "AÑADIR AL CARRITO →" : "PRODUCTO AGOTADO"%>
                            </button>
                        </form>
                    </article>
                </div>
            </div>
            <%
                }
            } else {
            %>
            <div class="no-results">
                <p>No encontramos neumáticos con estas especificaciones</p>
                <p>Por favor, intenta con otras medidas o contáctanos</p>
                <a style="color: white; font-size: 1.5rem; cursor: pointer;" href="index.html">Regresar al inicio <i class="bi bi-arrow-return-left"></i></a>
            </div>
            <%
                }
            %>
        </section>


    </body>

</html>
