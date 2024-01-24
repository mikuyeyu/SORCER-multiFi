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
    private final static String OWNERFINDER_GET_DETAILS_METHOD_NAME = "getDetails";
    private final static String OWNERFINDER_FIND_BY_FLAT_NAME_METHOD_NAME = "findByFlatName";

    @Test
    public void ownerFinderGetDetailsStructuredBlock() throws Exception {
        // Arrange
        Owner owner = OwnerStore.owners.stream().findFirst()
                .orElseThrow(() -> new NoSuchElementException("Owner not found"));

        Task t3 = task("t3", sig(OWNERFINDER_GET_DETAILS_METHOD_NAME, OwnerFinderImpl.class),
                context(OWNERFINDER_GET_DETAILS_METHOD_NAME, inVal(OwnerFinderImpl.OWNER_ARG_LASTNAME),
                        result(OwnerFinderImpl.OWNER_RESULT, Signature.Direction.OUT)));

        Block block = block("block", t3,
                context(inVal(OwnerFinderImpl.OWNER_ARG_LASTNAME, owner.getLastName())));

        // Act
        Block result = exert(block);

        // Assert
        assertEquals(owner, value(context(result), OwnerFinderImpl.OWNER_RESULT));
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

        Task t3 = task("t3", sig(OWNERFINDER_FIND_BY_FLAT_NAME_METHOD_NAME, OwnerFinderImpl.class),
                context(OWNERFINDER_FIND_BY_FLAT_NAME_METHOD_NAME, inVal(OwnerFinderImpl.OWNER_ARG_FLATNAME),
                        result(OwnerFinderImpl.OWNER_RESULT, Signature.Direction.OUT)));

        Block block = block("block", t3,
                context(inVal(OwnerFinderImpl.OWNER_ARG_FLATNAME, flat.street)));

        // Act
        Block result = exert(block);

        // Assert
        assertEquals(owner, value(context(result), OwnerFinderImpl.OWNER_RESULT));
    }
}
