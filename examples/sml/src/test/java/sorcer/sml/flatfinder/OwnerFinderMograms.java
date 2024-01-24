package sorcer.sml.flatfinder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sorcer.test.ProjectContext;
import org.sorcer.test.SorcerTestRunner;
import sorcer.flatfinder.*;
import sorcer.service.Block;
import sorcer.service.Signature;
import sorcer.service.Task;
import java.util.Arrays;
import java.util.NoSuchElementException;
import static org.junit.Assert.assertEquals;
import static sorcer.co.operator.inVal;
import static sorcer.eo.operator.*;
import static sorcer.mo.operator.value;
import static sorcer.so.operator.exert;

@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/sml")
public class OwnerFinderMograms {
    @Test
    public void ownerFinderGetDetailsStructuredBlock() throws Exception {
        // Arrange
        Owner owner = OwnerStore.owners.stream().findFirst()
                .orElseThrow(() -> new NoSuchElementException("Owner not found"));

        Task t3 = task("t3", sig("getDetails", OwnerFinderImpl.class),
                context("getDetails", inVal("arg/lastName"),
                        result("result/details", Signature.Direction.OUT)));

        Block block = block("block", t3,
                context(inVal("arg/lastName", owner.getLastName())));

        // Act
        Block result = exert(block);

        // Assert
        assertEquals(owner.toString(), value(context(result), "result/details"));
    }

    @Test
    public void ownerFinderFindByFlatNameStructuredBlock() throws Exception {
        // Arrange
        Flat flat = FlatStore.flats.values().stream().findFirst()
                .orElseThrow(() -> new NoSuchElementException("Flat not found"));
        Owner owner = OwnerStore.owners.stream()
                .filter(o -> Arrays.stream(o.getFlats()).anyMatch(f -> f.street.equals(flat.street)))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Owner not found"));

        Task t3 = task("t3", sig("findByFlatName", OwnerFinderImpl.class),
                context("findByFlatName", inVal("arg/flatName"),
                        result("result/owner", Signature.Direction.OUT)));

        Block block = block("block", t3,
                context(inVal("arg/flatName", flat.street)));

        // Act
        Block result = exert(block);

        // Assert
        assertEquals(owner, value(context(result), "result/owner"));
    }
}
