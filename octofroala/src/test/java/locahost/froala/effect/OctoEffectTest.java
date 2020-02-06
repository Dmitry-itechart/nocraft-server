package locahost.froala.effect;

import localhost.froala.effect.CommitEffectImpl;
import localhost.froala.effect.KickPullOctoEffect;
import localhost.froala.effect.OctoEffect;
import localhost.froala.impl.OctopathImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OctoEffectTest {

    @Test
    void testCreateSimpleCommitEffect() {
        OctoEffect effect = new CommitEffectImpl()
                .affectedFiles(Collections.emptyList())
                .isOk(false).setPrevious(null);

        assertFalse(effect.isOk());
        assertFalse(effect.previous().isPresent());
        assertEquals(Collections.emptyList(), effect.getAffectedFiles());
    }

    @Test
    void testComplexEffect() {
        var path1 = new OctopathImpl("/assets/", "file1.txt");
        var path2 = new OctopathImpl("/assets/", "file2.txt");

        OctoEffect effect = new CommitEffectImpl()
                .affectedFiles(Arrays.asList(path1, path2))
                .isOk(true);

        OctoEffect kp = new KickPullOctoEffect().code(200)
                .url("http://localhost:4200").setPrevious(effect);

        assertTrue(kp.isOk());

        assertEquals(2, kp.getAffectedFiles().size());
    }

    @Test
    void testComplexEffect2() {
        var path1 = new OctopathImpl("/assets/", "file1.txt");
        var path2 = new OctopathImpl("/assets/", "file1.txt");
        OctoEffect effect1 = new CommitEffectImpl()
                .affectedFiles(Arrays.asList(path1, path2))
                .isOk(false);

        var path3 = new OctopathImpl("/assets/", "file3.txt");
        var path4 = new OctopathImpl("/assets/", "file4.txt");
        OctoEffect effect2 = new CommitEffectImpl()
                .affectedFiles(Arrays.asList(path3, path4))
                .isOk(true).setPrevious(effect1);

        assertFalse(effect2.isOk());

        assertEquals(4, effect2.getAffectedFiles().size());
    }
}
