<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.gripster.model.Sucursal" %>
<%@ page import="com.gripster.model.Carrito" %>
<%
    List<Sucursal> sucursales = (List<Sucursal>) request.getAttribute("sucursales");
    List<Carrito> cartItems = (List<Carrito>) request.getAttribute("cartItems");
    double subtotal = (Double) request.getAttribute("subtotal");
    double iva = (Double) request.getAttribute("iva");
    double total = (Double) request.getAttribute("total");
    java.time.LocalDate hoy = java.time.LocalDate.now();
    java.time.LocalDate max = hoy.plusDays(7);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="Images/company/1.png">
        
        <title>Gripster: Tu Futuro Comienza Aquí | Gripster</title>
        
        <!-- Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700&family=Space+Grotesk:wght@400;500;600&display=swap" rel="stylesheet">
        
        <!-- Styles -->
        <link rel="stylesheet" href="Styles/general/navbar-white.css">
        <link rel="stylesheet" href="Styles/general/allStyles.css"/>
        <link rel="stylesheet" href="Styles/ckeckout-styles/checkout-styles-main.css">
        
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
    <body class="checkout-page">
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

        <div class="checkout-container">
            <div class="checkout-header">
                <h1 class="checkout-title">Finalizar Compra</h1>
            </div>
            
            <!-- Formulario de compra -->
            <form method="post" action="SvCheckout" class="checkout-form-section">
                <h2 class="section-title">
                    <i class="bi bi-person-circle"></i>Tus datos
                </h2>
                
                <div class="form-group">
                    <label for="nombre" class="form-label">Nombre completo</label>
                    <input type="text" id="nombre" name="nombre" class="form-control" required>
                </div>
                
                <div class="form-group">
                    <label for="correo" class="form-label">Correo electrónico</label>
                    <input type="email" id="correo" name="correo" class="form-control" required>
                </div>
                
                <div class="form-group">
                    <label for="telefono" class="form-label">Teléfono</label>
                    <input type="tel" id="telefono" name="telefono" class="form-control" required>
                </div>
                
                <h2 class="section-title">
                    <i class="bi bi-geo-alt"></i>Sucursal de instalación
                </h2>
                <div class="form-group select-wrapper">
                    <label for="idSucursal" class="form-label">Selecciona una sucursal</label>
                    <select id="idSucursal" name="idSucursal" class="form-control" required>
                        <option value="">-- Selecciona sucursal --</option>
                        <% for (Sucursal s : sucursales) {%>
                        <option value="<%= s.getIdSucursal()%>">
                            <%= s.getNombre()%> - <%= s.getAlcaldia()%>
                        </option>
                        <% }%>
                    </select>
                </div>
                
                <h2 class="section-title">
                    <i class="bi bi-calendar3"></i>Fecha de tu visita
                </h2>
                <div class="form-group date-picker-container">
                    <label for="fechaCita" class="form-label">Selecciona una fecha (hasta 7 días desde hoy)</label>
                    <input type="date" id="fechaCita" name="fechaCita" class="form-control" 
                           min="<%= hoy%>" max="<%= max%>" required>
                </div>
                <p class="text-muted"><small>Horario de atención: Lunes a domingo, de 10:00 am a 6:00 pm</small></p>
                
                <div class="payment-section">
                    <h3 class="payment-title">
                        <i class="bi bi-credit-card"></i>Método de pago
                    </h3>
                    <p>Transferencia bancaria</p>
                    
                    <div class="payment-details">
                        <p>Realiza el pago mediante transferencia a nuestra cuenta bancaria:</p>
                        <p><strong>Banco:</strong> BBVA</p>
                        <p><strong>Nombre:</strong> Xxtravaganzza Company S.A. de C.V.</p>
                        <p><strong>CLABE:</strong> 0121 8000 5678 9012 3456 7</p>
                        <p><strong>Número de cuenta:</strong> 0112 3456 7890 1234</p>
                        
                        <p><strong>¡Importante!</strong> Después de realizar el pago:</p>
                        <ol>
                            <li>Envía el comprobante al WhatsApp: <strong>55 1234 5678</strong></li>
                            <li>Presiona el botón "Confirmar Compra" para generar tu folio de seguimiento</li>
                        </ol>
                    </div>
                </div>
                
                <button type="submit" class="btn-confirm">
                    <i class="bi bi-check-circle"></i> Confirmar Compra
                </button>
            </form>
            
            <!-- Resumen del pedido -->
            <div class="order-summary">
                <h2 class="section-title">
                    <i class="bi bi-receipt"></i>Resumen de tu compra
                </h2>
                
                <div class="order-items">
                    <% for (Carrito item : cartItems) { %>
                    <div class="order-item">
                        <div>
                            <span class="item-name"><%= item.getLlanta().getMarcaNombre()%></span>
                            <span class="item-quantity">×<%= item.getCantidad()%></span>
                        </div>
                        <span class="item-price">$<%= String.format("%.2f", item.getLlanta().getPrecio() * item.getCantidad())%></span>
                    </div>
                    <% } %>
                </div>
                
                <div class="order-totals">
                    <div class="total-row">
                        <span class="total-label">Subtotal</span>
                        <span class="total-value">$<%= String.format("%.2f", subtotal)%></span>
                    </div>
                    
                    <div class="total-row">
                        <span class="total-label">IVA (16%)</span>
                        <span class="total-value">$<%= String.format("%.2f", iva)%></span>
                    </div>
                    
                    <div class="total-row grand-total">
                        <span class="total-label">Total a pagar</span>
                        <span class="total-value">$<%= String.format("%.2f", total)%></span>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>