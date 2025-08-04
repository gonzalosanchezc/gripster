<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.gripster.model.Carrito, com.gripster.model.Llanta, java.util.List" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0
            response.setDateHeader("Expires", 0); // Proxies
        %>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="Images/company/1.png">

        <title>Carrito de Compras | Gripster</title>

        <!-- Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700&family=Space+Grotesk:wght@400;500;600&display=swap" rel="stylesheet">

        <!-- Styles -->
        <link rel="stylesheet" href="Styles/general/navbar-white.css">
        <link rel="stylesheet" href="Styles/general/allStyles.css"/>
        <link rel="stylesheet" href="Styles/shopping-car/general-car-styles.css">

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
    <body class="cart-page">

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
                                <i class="bi bi-bag-plus"></i> Agregar más productos
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

        <div class="cart-container">
            <h1 class="cart-title">Tu Carrito</h1>

            <% List<Carrito> cartItems = (List<Carrito>) request.getAttribute("cartItems");
                if (cartItems != null && !cartItems.isEmpty()) { %>
            <div class="cart-items">
                <% for (Carrito item : cartItems) {%>
                <div class="cart-item">
                    <div class="item-image">
                        <img src="<%= item.getLlanta().getImagen()%>" alt="<%= item.getLlanta().getModeloNombre()%>">
                    </div>
                    <div class="item-details">
                        <span class="item-brand"><%= item.getLlanta().getMarcaNombre()%></span>
                        <span class="item-model"><%= item.getLlanta().getModeloNombre()%></span>
                        <div class="item-specs">
                            <span class="item-spec"><%= item.getLlanta().getAncho()%>/<%= item.getLlanta().getPerfil()%>R<%= item.getLlanta().getDiametro()%></span>
                            <span class="item-spec">Cantidad: <%= item.getCantidad()%></span>
                        </div>
                        <span class="item-price">$<%= String.format("%,.2f", item.getLlanta().getPrecio())%> MXN</span>
                    </div>
                    <div class="item-actions">
                        <form action="SvCarrito" method="POST" class="quantity-control">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="tireId" value="<%= item.getLlanta().getIdLlanta()%>">
                            <input type="number" name="quantity" value="<%= item.getCantidad()%>" min="1" max="10">
                            <button type="submit" class="btn-update">
                                <i class="bi bi-arrow-clockwise"></i> Actualizar
                            </button>
                        </form>
                        <form action="SvCarrito" method="POST">
                            <input type="hidden" name="action" value="remove">
                            <input type="hidden" name="tireId" value="<%= item.getLlanta().getIdLlanta()%>">
                            <button type="submit" class="btn-remove">
                                <i class="bi bi-trash"></i> Eliminar
                            </button>
                        </form>
                    </div>
                </div>
                <% } %>
            </div>

            <%
                double subtotal = 0.0;
                final double IVA = 0.16;

                for (Carrito item : cartItems) {
                    double precioUnitario = item.getLlanta().getPrecio();
                    int cantidad = item.getCantidad();
                    subtotal += precioUnitario * cantidad;
                }

                double impuesto = subtotal * IVA;
                double total = subtotal + impuesto;
            %>

            <div class="cart-summary">
                <h3 class="summary-title">Resumen de Compra</h3>
                <div class="summary-row">
                    <span class="summary-label">Subtotal:</span>
                    <span class="summary-value">$<%= String.format("%,.2f", subtotal)%> MXN</span>
                </div>
                <div class="summary-row">
                    <span class="summary-label">IVA (16%):</span>
                    <span class="summary-value">$<%= String.format("%,.2f", impuesto)%> MXN</span>
                </div>
                <div class="summary-row">
                    <span class="summary-label">Costo de instalación:</span>
                    <span class="summary-value">Gratis</span>
                </div>
                <div class="summary-row">
                    <span class="summary-label">Total:</span>
                    <span class="summary-value summary-total">$<%= String.format("%,.2f", total)%> MXN</span>
                </div>
                <a href="SvCheckout" class="btn-checkout">Proceder al Pago</a>
            </div>
            <% } else { %>
            <div class="empty-cart">
                <h2 class="empty-title">Tu carrito está vacío</h2>
                <p>Parece que no has agregado ningún producto a tu carrito todavía.</p>
                <p>Explora nuestro catálogo y encuentra las mejores llantas para tu vehículo.</p>
                <a href="index.html" class="btn-continue">Continuar Comprando</a>
            </div>
            <% }%>
        </div>
    </body>
</html>