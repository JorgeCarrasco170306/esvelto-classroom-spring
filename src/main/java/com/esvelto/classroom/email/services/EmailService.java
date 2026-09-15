package com.esvelto.classroom.email.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.modulith.NamedInterface;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@NamedInterface
@Service
public class EmailService {

    private final JavaMailSender mailSender;

    private static final String REMITENTE_EMAIL = "esvelto@resend.dev";
    private static final String REMITENTE_NOMBRE = "Esvelto";
    private static final String LOGO_URL = "https://tu-servidor.com/logo-esvelto.png";

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void enviarCodigoVerificacion(String destinatario, String codigo) {
        String htmlContent = """
            <!DOCTYPE html>
            <html lang="es">
            <body style="font-family: Arial, sans-serif; background-color: #f8fafc; margin: 0; padding: 24px;">
                <div style="max-width: 480px; margin: 0 auto; background-color: #ffffff; padding: 32px; border-radius: 12px; border: 1px solid #e2e8f0;">
                    <div style="text-align: center; margin-bottom: 24px;">
                        <img src="%s" alt="Esvelto" style="max-width: 140px; height: auto;" />
                    </div>
                    <h2 style="color: #0f172a; text-align: center; font-size: 22px; margin-top: 0;">Verifica tu cuenta</h2>
                    <p style="color: #475569; font-size: 15px; line-height: 1.6;">
                        ¡Te damos la bienvenida a Esvelto! Ingresa este código de 6 dígitos para verificar tu cuenta:
                    </p>
                    <div style="text-align: center; margin: 28px 0;">
                        <span style="display: inline-block; font-size: 32px; font-weight: bold; letter-spacing: 8px; color: #2563eb; background-color: #eff6ff; padding: 14px 28px; border-radius: 8px;">
                            %s
                        </span>
                    </div>
                    <p style="color: #64748b; font-size: 13px; text-align: center; margin: 0;">
                        Este código caduca en 10 minutos. Si tú no creaste esta cuenta, descarta este correo.
                    </p>
                </div>
            </body>
            </html>
            """.formatted(LOGO_URL, codigo);

        enviarCorreoHtml(destinatario, "Verifica tu cuenta - Esvelto", htmlContent);
    }

    @Async
    public void notificarInicioSesion(String destinatario, String fechaHora, String ip) {
        String htmlContent = """
            <!DOCTYPE html>
            <html lang="es">
            <body style="font-family: Arial, sans-serif; background-color: #f8fafc; margin: 0; padding: 24px;">
                <div style="max-width: 480px; margin: 0 auto; background-color: #ffffff; padding: 32px; border-radius: 12px; border: 1px solid #e2e8f0;">
                    <div style="text-align: center; margin-bottom: 24px;">
                        <img src="%s" alt="Esvelto" style="max-width: 140px; height: auto;" />
                    </div>
                    <h2 style="color: #0f172a; text-align: center; font-size: 20px; margin-top: 0;">Nuevo inicio de sesión</h2>
                    <p style="color: #475569; font-size: 14px; line-height: 1.6;">
                        Se detectó un acceso a tu cuenta de Esvelto con los siguientes detalles:
                    </p>
                    <ul style="color: #334155; font-size: 14px; line-height: 1.8; padding-left: 20px;">
                        <li><strong>Fecha y hora:</strong> %s</li>
                        <li><strong>Dirección IP:</strong> %s</li>
                    </ul>
                    <p style="color: #dc2626; font-size: 13px; margin-top: 20px;">
                        Si no fuiste tú, te recomendamos restablecer tu contraseña inmediatamente.
                    </p>
                </div>
            </body>
            </html>
            """.formatted(LOGO_URL, fechaHora, ip);

        enviarCorreoHtml(destinatario, "Alerta de seguridad: inicio de sesión en Esvelto", htmlContent);
    }

    private void enviarCorreoHtml(String destinatario, String asunto, String cuerpoHtml) {
        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

            helper.setFrom(REMITENTE_EMAIL, REMITENTE_NOMBRE);
            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setText(cuerpoHtml, true);

            mailSender.send(mensaje);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}