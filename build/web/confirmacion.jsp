<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.gripster.model.Venta" %>
<%@ page import="com.gripster.model.DetalleVenta" %>
<%@ page import="com.gripster.model.Sucursal" %>
<%@ page import="com.gripster.model.Llanta" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>

<%
    Venta venta = (Venta) request.getAttribute("venta");
    List<DetalleVenta> detalles = (List<DetalleVenta>) request.getAttribute("detalles");
    Sucursal sucursal = (Sucursal) request.getAttribute("sucursal");
    Map<Integer, Llanta> llantasMap = (Map<Integer, Llanta>) request.getAttribute("llantasMap");
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("es", "MX"));
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="Images/company/1.png">

        <title>Gripster: Llegando a tu destino | Gripster</title>

        <!-- Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700&family=Space+Grotesk:wght@400;500;600&display=swap" rel="stylesheet">

        <!-- Styles -->
        <link rel="stylesheet" href="Styles/general/navbar-white.css">
        <link rel="stylesheet" href="Styles/general/allStyles.css"/>
        <link rel="stylesheet" href="Styles/conf-styles-g/conf-main-styles.css"/>


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
    <body class="confirmation-page">
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
        
        <main class="confirmation-container">
            <section class="confirmation-header">
                <div class="confirmation-icon">
                    <i class="bi bi-check-circle-fill"></i>
                </div>
                <h1 class="confirmation-title">¡Compra Confirmada!</h1>
                <p class="confirmation-subtitle">Gracias por tu compra en Gripster. Aquí están los detalles de tu pedido.</p>
            </section>

            <div class="confirmation-grid">
                <section class="order-summary">
                    <h2 class="section-title">Resumen del Pedido</h2>
                    <div class="summary-card">
                        <div class="summary-row">
                            <span class="summary-label">Código de Pedido:</span>
                            <span class="summary-value"><%= venta.getCodigoVenta()%></span>
                        </div>
                        <div class="summary-row">
                            <span class="summary-label">Fecha de cita:</span>
                            <span class="summary-value"><%= venta.getFechaCita()%></span>
                        </div>
                        <div class="summary-row">
                            <span class="summary-label">Pago:</span>
                            <span class="summary-value status-badge"><%= venta.getEstado()%></span>
                        </div>
                    </div>
                </section>

                <section class="order-details">
                    <h2 class="section-title">Detalles de los Productos</h2>
                    <div class="products-table">
                        <div class="table-header">
                            <div class="header-item">Producto</div>
                            <div class="header-item">Cantidad</div>
                            <div class="header-item">Precio Unitario</div>
                            <div class="header-item">Subtotal</div>
                        </div>
                        <% for (DetalleVenta detalle : detalles) {
                                Llanta llanta = llantasMap.get(detalle.getIdLlanta());
                        %>
                        <div class="table-row">
                            <div class="table-cell product-cell">
                                <span class="product-name"><%= llanta != null ? llanta.getMarcaNombre() + " " + llanta.getModeloNombre() : "Producto no encontrado"%></span>
                            </div>
                            <div class="table-cell"><%= detalle.getCantidad()%></div>
                            <div class="table-cell"><%= currencyFormat.format(detalle.getPrecioUnitario())%></div>
                            <div class="table-cell"><%= currencyFormat.format(detalle.getPrecioUnitario() * detalle.getCantidad())%></div>
                        </div>
                        <% }%>
                    </div>
                    
                    <div class="order-total">
                        <span class="total-label">Total:</span>
                        <span class="total-amount"><%= currencyFormat.format(venta.getTotal())%></span>
                    </div>
                </section>

                <section class="installation-info">
                    <h2 class="section-title">Sucursal de Instalación</h2>
                    <div class="location-card">
                        <div class="location-icon">
                            <i class="bi bi-geo-alt-fill"></i>
                        </div>
                        <div class="location-details">
                            <h3 class="location-name"><%= sucursal.getNombre()%></h3>
                            <p class="location-address"><%= sucursal.getAlcaldia()%></p>
                        </div>
                    </div>
                </section>
            </div>

            <div class="confirmation-actions">
                <a href="index.html" class="btn-back">
                    <i class="bi bi-arrow-left"></i> Volver al inicio
                </a>
                <button class="btn-print" onclick="window.print()">
                    <i class="bi bi-printer-fill"></i> Imprimir Comprobante
                </button>
            </div>
        </main>
    </body>
</html>