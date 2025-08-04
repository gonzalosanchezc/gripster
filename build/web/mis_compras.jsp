<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="Images/company/1.png">

        <title>Mis Compras | Gripster</title>

        <!-- Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700&family=Space+Grotesk:wght@400;500;600&display=swap" rel="stylesheet">

        <!-- Styles -->
        <link rel="stylesheet" href="Styles/general/navbar-white.css">
        <link rel="stylesheet" href="Styles/general/allStyles.css">

        
        <!-- Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css">
        
        <link rel="stylesheet" href="Styles/pedidos-hechos-styles/purchase.css"/>
        <!-- Bootstrap Icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    </head>
    <body class="purchases-page">
        <!-- Navbar -->
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
                            <a class="nav-link underline-link" href="index.html">
                                <i class="bi bi-arrow-left"></i> Seguir comprando
                            </a>
                        </li>
                    </ul>

                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link special-link" href="index.html">
                                <i class="bi bi-box-arrow-right"></i> Cerrar sesión
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Contenido principal -->
        <main class="purchases-container">
            <header class="purchases-header">
                <h1><i class="bi bi-receipt"></i> Mis Compras</h1>
                <p>Historial de todas tus compras en Gripster</p>
            </header>

            <div class="purchases-grid">
                <!-- Compra 1 -->
                <div class="purchase-card">
                    <div class="purchase-header">
                        <div class="purchase-info">
                            <h3>Pedido #GRP-9555</h3>
                            <span class="purchase-date">01/07/2025 - 11:36 p.m.</span>
                            <span class="purchase-status completed">Completado</span>
                        </div>
                        <div class="purchase-total">
                            <span>Total</span>
                            <strong>$9,990.00 MXN</strong>
                        </div>
                    </div>

                    <div class="purchase-details">
                        <div class="product-item">
                            <img src="tires/sport/1s.webp" alt="Michelin Pilot Sport 4">
                            <div class="product-info">
                                <h4>Michelin Pilot Sport 4</h4>
                                <span>225/40R18 - Alto Rendimiento</span>
                                <div class="product-meta">
                                    <span>2 unidades</span>
                                    <span>$3,120.00 c/u</span>
                                </div>
                            </div>
                        </div>

                        <div class="product-item">
                            <img src="tires/sport/2s.webp" alt="Pirelli P Zero">
                            <div class="product-info">
                                <h4>Pirelli P Zero</h4>
                                <span>235/35R19 - Ultra High Performance</span>
                                <div class="product-meta">
                                    <span>2 unidades</span>
                                    <span>$3,690.00 c/u</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="purchase-footer">
                        <div class="delivery-info">
                            <i class="bi bi-shop"></i>
                            <div>
                                <span>Sucursal de instalación</span>
                                <strong>Sucursal Centro - Av. Juárez 50</strong>
                            </div>
                        </div>
                        <button class="view-details">
                            <i class="bi bi-eye"></i> Ver detalles
                        </button>
                    </div>
                </div>

                <!-- Compra 2 -->
                <div class="purchase-card">
                    <div class="purchase-header">
                        <div class="purchase-info">
                            <h3>Pedido #GRP-9558</h3>
                            <span class="purchase-date">28/06/2025 - 3:15 p.m.</span>
                            <span class="purchase-status completed">Completado</span>
                        </div>
                        <div class="purchase-total">
                            <span>Total</span>
                            <strong>$7,380.00 MXN</strong>
                        </div>
                    </div>

                    <div class="purchase-details">
                        <div class="product-item">
                            <img src="tires/sport/2s.webp" alt="Pirelli P Zero">
                            <div class="product-info">
                                <h4>Pirelli P Zero</h4>
                                <span>235/35R19 - Ultra High Performance</span>
                                <div class="product-meta">
                                    <span>2 unidades</span>
                                    <span>$3,690.00 c/u</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="purchase-footer">
                        <div class="delivery-info">
                            <i class="bi bi-shop"></i>
                            <div>
                                <span>Sucursal de instalación</span>
                                <strong>Sucursal Sur - Calz. de Tlalpan 1035</strong>
                            </div>
                        </div>
                        <button class="view-details">
                            <i class="bi bi-eye"></i> Ver detalles
                        </button>
                    </div>
                </div>

                <!-- Compra 3 -->
                <div class="purchase-card">
                    <div class="purchase-header">
                        <div class="purchase-info">
                            <h3>Pedido #GRP-7722</h3>
                            <span class="purchase-date">15/06/2025 - 10:42 a.m.</span>
                            <span class="purchase-status completed">Completado</span>
                        </div>
                        <div class="purchase-total">
                            <span>Total</span>
                            <strong>$5,760.00 MXN</strong>
                        </div>
                    </div>

                    <div class="purchase-details">
                        <div class="product-item">
                            <img src="tires/sport/5s.webp" alt="Yokohama ADVAN Sport">
                            <div class="product-info">
                                <h4>Yokohama ADVAN Sport</h4>
                                <span>235/45R17 - Deportiva</span>
                                <div class="product-meta">
                                    <span>2 unidades</span>
                                    <span>$2,880.00 c/u</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="purchase-footer">
                        <div class="delivery-info">
                            <i class="bi bi-shop"></i>
                            <div>
                                <span>Sucursal de instalación</span>
                                <strong>Sucursal Norte - Av. Insurgentes Norte 800</strong>
                            </div>
                        </div>
                        <button class="view-details">
                            <i class="bi bi-eye"></i> Ver detalles
                        </button>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>