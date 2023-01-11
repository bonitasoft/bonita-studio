package org.bonitasoft.studio.application.maven.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.io.File;
import java.nio.file.Files;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sonatype.plexus.components.cipher.DefaultPlexusCipher;
import org.sonatype.plexus.components.cipher.PlexusCipher;

public class MavenPasswordManagerTest {

    private File securityFile;

    MavenPasswordManager pwdManager = spy(MavenPasswordManager.class);

    @BeforeEach
    public void init() throws Exception {
        securityFile = Files.createTempFile("settings-security", ".xml").toFile();

        PlexusCipher encrypter = new DefaultPlexusCipher();
        doReturn(securityFile).when(pwdManager).getSecurityFile();
        doReturn(encrypter).when(pwdManager).getCypher();
    }

    @AfterEach
    public void clean() throws Exception {
        securityFile.delete();
    }

    @Test
    public void should_create_and_update_master_password() {
        assertThat(securityFile).exists();
        assertThat(pwdManager.getCurrentMasterPassword()).isEmpty();

        String encryptedMasterPassword = pwdManager.encryptMasterPassword("Adrien");
        pwdManager.updateMasterPassword(encryptedMasterPassword);

        Optional<String> masterPwd = pwdManager.getCurrentMasterPassword();
        assertThat(masterPwd)
            .isPresent()
            .hasValue(encryptedMasterPassword);

        String newEncryptedMasterPassword = pwdManager.encryptMasterPassword("adrien");
        assertThat(newEncryptedMasterPassword).isNotEqualTo(encryptedMasterPassword);
        pwdManager.updateMasterPassword(newEncryptedMasterPassword);

        Optional<String> newMasterPwd = pwdManager.getCurrentMasterPassword();
        assertThat(newMasterPwd)
            .isPresent()
            .hasValue(newEncryptedMasterPassword);
    }
    
    @Test
    public void should_encrypt_and_decrypt_password() {
        assertThat(securityFile).exists();
        assertThat(pwdManager.getCurrentMasterPassword()).isEmpty();

        String encryptedMasterPassword = pwdManager.encryptMasterPassword("Adrien");
        pwdManager.updateMasterPassword(encryptedMasterPassword);

        var encryptedPassowrd = pwdManager.encryptPassword("1Mi$$U");
        assertThat(encryptedPassowrd).isNotEqualTo("1Mi$$U");
        assertThat(pwdManager.decryptPassword(encryptedPassowrd))
            .isEqualTo("1Mi$$U");
    }

}
