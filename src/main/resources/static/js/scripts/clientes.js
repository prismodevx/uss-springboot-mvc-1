const url = "/v1/clientes";

function ajaxRequest(type, endpoint, data = null) {
    return $.ajax({
        type,
        url: endpoint,
        data: data ? JSON.stringify(data) : null,
        dataType: "json",
        contentType: data ? "application/json" : undefined,
        cache: false,
        timeout: 600000,
    });
}

function save(bandera) {
    const id = $("#guardar").data("id");
    const registro = {
        id,
        nombre: $("#nombre").val(),
        tipoDocumento: $("#tipoDocumento").val(),
        numeroDocumento: $("#numeroDocumento").val(),
        telefono: $("#telefono").val(),
        email: $("#email").val(),
    };

    const type = bandera === 1 ? "POST" : "PUT";
    const endpoint = bandera === 1 ? url : `${url}/${id}`;

    ajaxRequest(type, endpoint, registro)
        .done((data) => {
            if (data.ok) {
                $("#modal-update").modal("hide");
                getTabla();
                $("#error-message").addClass("d-none");
                Swal.fire({
                    icon: 'success',
                    title: `Se ha ${bandera === 1 ? 'guardado' : 'actualizado'} el registro`,
                    showConfirmButton: false,
                    timer: 1500
                });
                clear();
            } else {
                showError(data.message);
            }
        }).fail(function (jqXHR) {
        let errorMessage = jqXHR.responseJSON && jqXHR.responseJSON.message ? jqXHR.responseJSON.message : "Error inesperado. Código: " + jqXHR.status;
        showError(errorMessage);
    });
}

function showError(message) {
    $("#error-message").text(message).removeClass("d-none");
}

function deleteFila(id) {
    ajaxRequest("DELETE", `${url}/${id}`)
        .done((data) => {
            if (data.ok) {
                Swal.fire({
                    icon: 'success',
                    title: 'Se ha eliminado el registro',
                    showConfirmButton: false,
                    timer: 1500
                });
                getTabla();
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: data.message,
                    confirmButtonText: 'Aceptar'
                });
            }
        })
        .fail(handleError);
}

function getTabla() {
    ajaxRequest("GET", url)
        .done((data) => {
            const t = $("#tablaRegistros").DataTable();
            t.clear().draw(false);

            if (data.ok) {
                $.each(data.body, (index, registro) => {
                    const botonera = `
                        <button type="button" class="btn btn-warning btn-sm editar">
                            <i class="fas fa-edit"></i>
                        </button>
                        <button type="button" class="btn btn-danger btn-sm eliminar">
                            <i class="fas fa-trash"></i>
                        </button>`;
                    let r;

                    if(registro.tipoDocumento == 1) {
                        r = "Documento no domiciliado sin RUC";
                    }
                    switch (registro.tipoDocumento) {
                        case "0":
                            r = "Documento no domiciliado sin RUC";
                            break;
                        case "1":
                            r = "DNI";
                            break;
                        case "4":
                            r = "Carnet de Extranjeria";
                            break;
                        case "6":
                            r = "RUC";
                            break;
                        case "7":
                            r = "Pasaporte";
                            break;
                    }
                    t.row.add([botonera, registro.id, registro.nombre, r, registro.numeroDocumento,registro.telefono, registro.email]);
                });
                t.draw(false);
            } else {
                console.error("Error en la respuesta: ", data.message);
            }
        })
        .fail(handleError);
}

function getFila(id) {
    ajaxRequest("GET", `${url}/${id}`)
        .done((data) => {
            if (data.ok) {
                $("#modal-title").text("Editar registro");
                $("#nombre").val(data.body.nombre);
                $("#tipoDocumento").val(data.body.tipoDocumento);
                $("#numeroDocumento").val(data.body.numeroDocumento);
                $("#telefono").val(data.body.telefono);
                $("#email").val(data.body.email);
                $("#guardar").data("id", data.body.id).data("bandera", 0);
                $("#modal-update").modal("show");
            } else {
                showError(data.message);
            }
        })
        .fail(handleError);
}

function clear() {
    $("#modal-title").text("Nuevo registro");
    $("#nombre").val("");
    $("#tipoDocumento").val("");
    $("#numeroDocumento").val("");
    $("#email").val("");
    $("#telefono").val("");

    $("#guardar").data("id", 0).data("bandera", 1);
}

function handleError(jqXHR) {
    const errorMessage = jqXHR.responseJSON?.message || `Error inesperado. Código: ${jqXHR.status}`;
    Swal.fire({
        icon: 'error',
        title: 'Error',
        text: errorMessage,
        confirmButtonText: 'Aceptar'
    });
}

$(document).ready(function () {
    $("#tablaRegistros").DataTable({
        language: {
            lengthMenu: "Mostrar _MENU_ registros",
            zeroRecords: "No se encontraron coincidencias",
            info: "Mostrando del _START_ al _END_ de _TOTAL_ registros",
            infoEmpty: "Sin resultados",
            search: "Buscar: ",
            paginate: {
                first: "Primero",
                last: "Último",
                next: "Siguiente",
                previous: "Anterior",
            },
        },
        columnDefs: [
            { targets: 0, orderable: false }
        ],
    });

    clear();

    $("#nuevo").click(clear);

    $("#guardar").click(() => save($("#guardar").data("bandera")));

    $(document).on('click', '.eliminar', function () {
        const id = $(this).closest('tr').find('td:eq(1)').text();
        Swal.fire({
            title: 'Eliminar registro',
            text: "¿Está seguro de querer eliminar este registro?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Si'
        }).then((result) => {
            if (result.isConfirmed) {
                deleteFila(id);
            }
        });
    });

    $(document).on('click', '.editar', function () {
        const id = $(this).closest('tr').find('td:eq(1)').text();
        getFila(id);
    });

    getTabla();

    $('#liAlmacen').addClass("menu-open");
    $('#liCliente').addClass("active");

});