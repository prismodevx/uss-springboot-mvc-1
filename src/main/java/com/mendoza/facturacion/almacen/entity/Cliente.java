package com.mendoza.facturacion.almacen.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "clientes")
@EntityListeners(AuditingEntityListener.class)
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", length = 70, nullable = false)
    private String nombre;

    @Column(name = "tipo_documento", length = 15, nullable = false)
    private String tipoDocumento;

    @Column(name = "numero_documento", length = 15, nullable = false)
    private String numeroDocumento;

    @Column(name = "telefono", length = 15, nullable = false)
    private String telefono;

    @Column(name = "email", length = 70, nullable = false)
    private String email;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
}
