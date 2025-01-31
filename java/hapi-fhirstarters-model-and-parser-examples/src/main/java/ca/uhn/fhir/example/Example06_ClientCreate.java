package ca.uhn.fhir.example;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.Patient;

public class Example06_ClientCreate {
   public static void main(String[] theArgs) {

      Patient pat = new Patient();
      pat.addName().setFamily("Simpson-test").addGiven("Homer").addGiven("Jtest");
      pat.addIdentifier().setSystem("http://fhir-training/example").setValue("fhir-training-id");
      pat.setGender(AdministrativeGender.MALE);

      // Create a context
      FhirContext ctx = FhirContext.forR4();

      // Create a client
      String serverBaseUrl = "http://hapi.fhir.org/baseR4";
      IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);

      // Use the client to store a new resource instance
      MethodOutcome outcome = client
         .create()
         .resource(pat)
         .execute();

      // Print the ID of the newly created resource
      System.out.println(outcome.getId());
      System.out.println("........................");
      System.out.println(ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(outcome.getResource()));
      //System.out.println(fhirContext.newXmlParser().setPrettyPrint(true).encodeResourceToString(pat));
      System.out.println("........................");
   }
}
