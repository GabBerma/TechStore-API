package cl.techstore.api.aws;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class SqsNotificationService {

    private static final String QUEUE_URL = "https://sqs.us-east-1.amazonaws.com/144287749868/techstore-product-queue";

    private final SqsClient sqsClient;

    public SqsNotificationService() {
        this.sqsClient = SqsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    public void enviarMensajeProductoCreado(Long productoId, String nombre, String categoria) {
        String mensaje = """
                {
                  "evento": "PRODUCTO_CREADO",
                  "productoId": %d,
                  "nombre": "%s",
                  "categoria": "%s",
                  "origen": "TechStore API Spring Boot"
                }
                """.formatted(productoId, nombre, categoria);

        SendMessageRequest request = SendMessageRequest.builder()
                .queueUrl(QUEUE_URL)
                .messageBody(mensaje)
                .build();

        sqsClient.sendMessage(request);

        System.out.println("Mensaje enviado a SQS: " + mensaje);
    }
}