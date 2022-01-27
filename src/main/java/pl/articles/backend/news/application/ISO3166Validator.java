package pl.articles.backend.news.application;

import java.util.Objects;
import java.util.regex.Pattern;

public class ISO3166Validator {

    private ISO3166Validator() {
        throw new IllegalStateException("This class should not be instantiated!");
    }

    private static final Pattern pattern = Pattern.compile("^A[^ABCHJKNPVY]|B[^CKPUX]|C[^BEJPQST]|D[EJKMOZ]|E[CEGHRST]|" +
                                                           "F[IJKMOR]|G[^CJKOVXZ]|H[KMNRTU]|I[DEL-OQ-T]|J[EMOP]|K[EGHIM" +
                                                           "NPRWYZ]|L[ABCIKR-VY]|M[^BIJ]|N[ACEFGILOPRUZ]|OM|P[AE-HK-NRS" +
                                                           "TWY]|QA|R[EOSUW]|S[^FPQUW]|T[^ABEIPQSUXY]|U[AGMSYZ]|V[ACEGI" +
                                                           "NU]|WF|WS|YE|YT|Z[AMW]$");

    public static boolean validate(String code) {
        return Objects.nonNull(code) && pattern.matcher(code.toUpperCase()).matches();
    }
}
