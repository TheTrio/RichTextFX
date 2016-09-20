package org.fxmisc.richtext;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;

import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.model.EditableStyledDocument;
import org.fxmisc.richtext.model.StyledText;
import org.fxmisc.richtext.model.StyledTextOps;

import javafx.scene.text.TextFlow;

/**
 * Text editing control. Accepts user input (keyboard, mouse) and
 * provides API to assign style to text ranges. It is suitable for
 * syntax highlighting and rich-text editors.
 *
 * <p>Subclassing is allowed to define the type of style, e.g. inline
 * style or style classes.</p>
 *
 * <p>Note: Scroll bars no longer appear when the content spans outside
 * of the viewport. To add scroll bars, the area needs to be wrapped in
 * a {@link VirtualizedScrollPane}. For example, </p>
 * <pre>
 * {@code
 * // shows area without scroll bars
 * InlineCssTextArea area = new InlineCssTextArea();
 *
 * // add scroll bars that will display as needed
 * VirtualizedScrollPane<InlineCssTextArea> vsPane = new VirtualizedScrollPane(area);
 *
 * Parent parent = //;
 * parent.getChildren().add(vsPane)
 * }
 * </pre>
 *
 * <h3>Overriding keyboard shortcuts</h3>
 *
 * {@code StyledTextArea} uses {@code KEY_TYPED} handler to handle ordinary
 * character input and {@code KEY_PRESSED} handler to handle control key
 * combinations (including Enter and Tab). To add or override some keyboard
 * shortcuts, while keeping the rest in place, you would combine the default
 * event handler with a new one that adds or overrides some of the default
 * key combinations. This is how to bind {@code Ctrl+S} to the {@code save()}
 * operation:
 * <pre>
 * {@code
 * import static javafx.scene.input.KeyCode.*;
 * import static javafx.scene.input.KeyCombination.*;
 * import static org.fxmisc.wellbehaved.event.EventPattern.*;
 * import static org.fxmisc.wellbehaved.event.InputMap.*;
 *
 * import org.fxmisc.wellbehaved.event.Nodes;
 *
 * Nodes.addInputMap(area, consume(keyPressed(S, CONTROL_DOWN), event -> save()));
 * }
 * </pre>
 *
 * @param <S> type of style that can be applied to text.
 */
public class StyledTextArea<PS, S> extends GenericRichtextArea<PS, StyledText<S>, S> {

    public StyledTextArea(PS initialParagraphStyle, BiConsumer<TextFlow, PS> applyParagraphStyle, S initialTextStyle,
            BiConsumer<? super TextExt, S> applyStyle, boolean preserveStyle) {
        super(initialParagraphStyle, applyParagraphStyle, initialTextStyle, new StyledTextOps<>(), applyStyle, preserveStyle);
    }

    public StyledTextArea(PS initialParagraphStyle, BiConsumer<TextFlow, PS> applyParagraphStyle, S initialTextStyle,
            BiConsumer<? super TextExt, S> applyStyle,
            EditableStyledDocument<PS, StyledText<S>, S> document, boolean preserveStyle) {
        super(initialParagraphStyle, applyParagraphStyle, initialTextStyle, new StyledTextOps<>(), applyStyle, document, preserveStyle);
    }

    public StyledTextArea(PS initialParagraphStyle, BiConsumer<TextFlow, PS> applyParagraphStyle, S initialTextStyle,
            BiConsumer<? super TextExt, S> applyStyle,
            EditableStyledDocument<PS, StyledText<S>, S> document) {
        super(initialParagraphStyle, applyParagraphStyle, initialTextStyle, new StyledTextOps<>(), applyStyle, document);
    }

    public StyledTextArea(PS initialParagraphStyle, BiConsumer<TextFlow, PS> applyParagraphStyle, S initialTextStyle,
            BiConsumer<? super TextExt, S> applyStyle) {
        super(initialParagraphStyle, applyParagraphStyle, initialTextStyle, new StyledTextOps<>(), applyStyle);
    }
}
