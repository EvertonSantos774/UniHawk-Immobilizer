package appinventor.ai_eva_tcctelemetria.EVA_imobilizer;

import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.AppInventorCompatActivity;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.Texting;
import com.google.appinventor.components.runtime.VerticalArrangement;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.errors.StopBlocksExecution;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.RetValManager;
import com.google.appinventor.components.runtime.util.RuntimeErrorAlert;
import com.google.youngandroid.C0718runtime;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.reflect.SlotSet;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lang.Promise;
import kawa.lib.C0731lists;
import kawa.lib.misc;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.require;

/* compiled from: recuperar_senha.yail */
public class recuperar_senha extends Form implements Runnable {
    static final SimpleSymbol Lit0 = ((SimpleSymbol) new SimpleSymbol("recuperar_senha").readResolve());
    static final SimpleSymbol Lit1 = ((SimpleSymbol) new SimpleSymbol("getMessage").readResolve());
    static final SimpleSymbol Lit10 = ((SimpleSymbol) new SimpleSymbol("CloseScreenAnimation").readResolve());
    static final SimpleSymbol Lit100 = ((SimpleSymbol) new SimpleSymbol("add-to-form-environment").readResolve());
    static final SimpleSymbol Lit101 = ((SimpleSymbol) new SimpleSymbol("lookup-in-form-environment").readResolve());
    static final SimpleSymbol Lit102 = ((SimpleSymbol) new SimpleSymbol("is-bound-in-form-environment").readResolve());
    static final SimpleSymbol Lit103 = ((SimpleSymbol) new SimpleSymbol("add-to-global-var-environment").readResolve());
    static final SimpleSymbol Lit104 = ((SimpleSymbol) new SimpleSymbol("add-to-events").readResolve());
    static final SimpleSymbol Lit105 = ((SimpleSymbol) new SimpleSymbol("add-to-components").readResolve());
    static final SimpleSymbol Lit106 = ((SimpleSymbol) new SimpleSymbol("add-to-global-vars").readResolve());
    static final SimpleSymbol Lit107 = ((SimpleSymbol) new SimpleSymbol("add-to-form-do-after-creation").readResolve());
    static final SimpleSymbol Lit108 = ((SimpleSymbol) new SimpleSymbol("send-error").readResolve());
    static final SimpleSymbol Lit109 = ((SimpleSymbol) new SimpleSymbol("dispatchEvent").readResolve());
    static final SimpleSymbol Lit11 = ((SimpleSymbol) new SimpleSymbol("OpenScreenAnimation").readResolve());
    static final SimpleSymbol Lit110 = ((SimpleSymbol) new SimpleSymbol("dispatchGenericEvent").readResolve());
    static final SimpleSymbol Lit111 = ((SimpleSymbol) new SimpleSymbol("lookup-handler").readResolve());
    static final SimpleSymbol Lit12 = ((SimpleSymbol) new SimpleSymbol("ScreenOrientation").readResolve());
    static final SimpleSymbol Lit13 = ((SimpleSymbol) new SimpleSymbol("ShowListsAsJson").readResolve());
    static final SimpleSymbol Lit14 = ((SimpleSymbol) new SimpleSymbol("Sizing").readResolve());
    static final SimpleSymbol Lit15 = ((SimpleSymbol) new SimpleSymbol("Theme").readResolve());
    static final SimpleSymbol Lit16 = ((SimpleSymbol) new SimpleSymbol("Title").readResolve());
    static final SimpleSymbol Lit17 = ((SimpleSymbol) new SimpleSymbol("TitleVisible").readResolve());
    static final SimpleSymbol Lit18 = ((SimpleSymbol) new SimpleSymbol("txt_informarNumero").readResolve());
    static final SimpleSymbol Lit19 = ((SimpleSymbol) new SimpleSymbol("Visible").readResolve());
    static final SimpleSymbol Lit2 = ((SimpleSymbol) new SimpleSymbol("*the-null-value*").readResolve());
    static final SimpleSymbol Lit20 = ((SimpleSymbol) new SimpleSymbol("recuperar_senha$Initialize").readResolve());
    static final SimpleSymbol Lit21 = ((SimpleSymbol) new SimpleSymbol("Initialize").readResolve());
    static final FString Lit22 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit23 = ((SimpleSymbol) new SimpleSymbol("Legenda1").readResolve());
    static final SimpleSymbol Lit24 = ((SimpleSymbol) new SimpleSymbol("Height").readResolve());
    static final IntNum Lit25 = IntNum.make(-1001);
    static final SimpleSymbol Lit26 = ((SimpleSymbol) new SimpleSymbol("TextColor").readResolve());
    static final IntNum Lit27 = IntNum.make(16777215);
    static final FString Lit28 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit29 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit3 = ((SimpleSymbol) new SimpleSymbol("ActionBar").readResolve());
    static final SimpleSymbol Lit30 = ((SimpleSymbol) new SimpleSymbol("btn_voltarTela").readResolve());
    static final IntNum Lit31 = IntNum.make(16777215);
    static final IntNum Lit32 = IntNum.make(-1005);
    static final SimpleSymbol Lit33 = ((SimpleSymbol) new SimpleSymbol(Component.LISTVIEW_KEY_IMAGE).readResolve());
    static final IntNum Lit34 = IntNum.make(16777215);
    static final SimpleSymbol Lit35 = ((SimpleSymbol) new SimpleSymbol("Width").readResolve());
    static final IntNum Lit36 = IntNum.make(-1010);
    static final FString Lit37 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit38 = PairWithPosition.make(Lit6, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/recuperar_senha.yail", 204878);
    static final SimpleSymbol Lit39 = ((SimpleSymbol) new SimpleSymbol("btn_voltarTela$Click").readResolve());
    static final SimpleSymbol Lit4 = ((SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN).readResolve());
    static final SimpleSymbol Lit40 = ((SimpleSymbol) new SimpleSymbol("Click").readResolve());
    static final FString Lit41 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    static final SimpleSymbol Lit42 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoVertical1").readResolve());
    static final SimpleSymbol Lit43 = ((SimpleSymbol) new SimpleSymbol("AlignHorizontal").readResolve());
    static final IntNum Lit44 = IntNum.make(3);
    static final IntNum Lit45;
    static final IntNum Lit46 = IntNum.make(-2);
    static final FString Lit47 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    static final FString Lit48 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit49 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal2").readResolve());
    static final SimpleSymbol Lit5 = ((SimpleSymbol) new SimpleSymbol("AppName").readResolve());
    static final IntNum Lit50 = IntNum.make(20);
    static final FString Lit51 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit52 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit53 = ((SimpleSymbol) new SimpleSymbol("txt_Titulo").readResolve());
    static final SimpleSymbol Lit54 = ((SimpleSymbol) new SimpleSymbol("FontSize").readResolve());
    static final IntNum Lit55 = IntNum.make(25);
    static final SimpleSymbol Lit56 = ((SimpleSymbol) new SimpleSymbol("Text").readResolve());
    static final SimpleSymbol Lit57 = ((SimpleSymbol) new SimpleSymbol("TextAlignment").readResolve());
    static final IntNum Lit58 = IntNum.make(1);
    static final IntNum Lit59;
    static final SimpleSymbol Lit6;
    static final FString Lit60 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit61 = new FString("com.google.appinventor.components.runtime.Label");
    static final IntNum Lit62 = IntNum.make(15);
    static final IntNum Lit63 = IntNum.make(-1010);
    static final IntNum Lit64;
    static final FString Lit65 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit66 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit67 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal3").readResolve());
    static final IntNum Lit68 = IntNum.make(5);
    static final FString Lit69 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit7 = ((SimpleSymbol) new SimpleSymbol("BackgroundColor").readResolve());
    static final FString Lit70 = new FString("com.google.appinventor.components.runtime.TextBox");
    static final SimpleSymbol Lit71 = ((SimpleSymbol) new SimpleSymbol("cx_numero").readResolve());
    static final IntNum Lit72 = IntNum.make(-1010);
    static final SimpleSymbol Lit73 = ((SimpleSymbol) new SimpleSymbol("Hint").readResolve());
    static final FString Lit74 = new FString("com.google.appinventor.components.runtime.TextBox");
    static final FString Lit75 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit76 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal1").readResolve());
    static final FString Lit77 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit78 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit79 = ((SimpleSymbol) new SimpleSymbol("btn_codigo").readResolve());
    static final IntNum Lit8;
    static final IntNum Lit80;
    static final IntNum Lit81;
    static final FString Lit82 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit83 = PairWithPosition.make(Lit6, PairWithPosition.make(Lit6, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/recuperar_senha.yail", 581739), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/recuperar_senha.yail", 581733);
    static final SimpleSymbol Lit84 = ((SimpleSymbol) new SimpleSymbol("sms_numero").readResolve());
    static final SimpleSymbol Lit85 = ((SimpleSymbol) new SimpleSymbol("Message").readResolve());
    static final SimpleSymbol Lit86 = ((SimpleSymbol) new SimpleSymbol("PhoneNumber").readResolve());
    static final SimpleSymbol Lit87 = ((SimpleSymbol) new SimpleSymbol("SendMessageDirect").readResolve());
    static final PairWithPosition Lit88 = PairWithPosition.make(Lit6, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/recuperar_senha.yail", 582070);
    static final SimpleSymbol Lit89 = ((SimpleSymbol) new SimpleSymbol("Notificador1").readResolve());
    static final SimpleSymbol Lit9 = ((SimpleSymbol) new SimpleSymbol("number").readResolve());
    static final SimpleSymbol Lit90 = ((SimpleSymbol) new SimpleSymbol("ShowAlert").readResolve());
    static final PairWithPosition Lit91;
    static final SimpleSymbol Lit92 = ((SimpleSymbol) new SimpleSymbol("btn_codigo$Click").readResolve());
    static final FString Lit93 = new FString("com.google.appinventor.components.runtime.Notifier");
    static final FString Lit94 = new FString("com.google.appinventor.components.runtime.Notifier");
    static final FString Lit95 = new FString("com.google.appinventor.components.runtime.Texting");
    static final SimpleSymbol Lit96 = ((SimpleSymbol) new SimpleSymbol("ReceivingEnabled").readResolve());
    static final FString Lit97 = new FString("com.google.appinventor.components.runtime.Texting");
    static final SimpleSymbol Lit98 = ((SimpleSymbol) new SimpleSymbol("get-simple-name").readResolve());
    static final SimpleSymbol Lit99 = ((SimpleSymbol) new SimpleSymbol("android-log-form").readResolve());
    static final ModuleMethod lambda$Fn1 = null;
    static final ModuleMethod lambda$Fn10 = null;
    static final ModuleMethod lambda$Fn11 = null;
    static final ModuleMethod lambda$Fn12 = null;
    static final ModuleMethod lambda$Fn13 = null;
    static final ModuleMethod lambda$Fn14 = null;
    static final ModuleMethod lambda$Fn15 = null;
    static final ModuleMethod lambda$Fn16 = null;
    static final ModuleMethod lambda$Fn17 = null;
    static final ModuleMethod lambda$Fn18 = null;
    static final ModuleMethod lambda$Fn19 = null;
    static final ModuleMethod lambda$Fn2 = null;
    static final ModuleMethod lambda$Fn20 = null;
    static final ModuleMethod lambda$Fn21 = null;
    static final ModuleMethod lambda$Fn22 = null;
    static final ModuleMethod lambda$Fn23 = null;
    static final ModuleMethod lambda$Fn24 = null;
    static final ModuleMethod lambda$Fn3 = null;
    static final ModuleMethod lambda$Fn4 = null;
    static final ModuleMethod lambda$Fn5 = null;
    static final ModuleMethod lambda$Fn6 = null;
    static final ModuleMethod lambda$Fn7 = null;
    static final ModuleMethod lambda$Fn8 = null;
    static final ModuleMethod lambda$Fn9 = null;
    public static recuperar_senha recuperar_senha;
    public Boolean $Stdebug$Mnform$St;
    public final ModuleMethod $define;
    public Label Legenda1;
    public Notifier Notificador1;

    /* renamed from: OrganizaçãoHorizontal1  reason: contains not printable characters */
    public HorizontalArrangement f436OrganizaoHorizontal1;

    /* renamed from: OrganizaçãoHorizontal2  reason: contains not printable characters */
    public HorizontalArrangement f437OrganizaoHorizontal2;

    /* renamed from: OrganizaçãoHorizontal3  reason: contains not printable characters */
    public HorizontalArrangement f438OrganizaoHorizontal3;

    /* renamed from: OrganizaçãoVertical1  reason: contains not printable characters */
    public VerticalArrangement f439OrganizaoVertical1;
    public final ModuleMethod add$Mnto$Mncomponents;
    public final ModuleMethod add$Mnto$Mnevents;
    public final ModuleMethod add$Mnto$Mnform$Mndo$Mnafter$Mncreation;
    public final ModuleMethod add$Mnto$Mnform$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvar$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvars;
    public final ModuleMethod android$Mnlog$Mnform;
    public Button btn_codigo;
    public final ModuleMethod btn_codigo$Click;
    public Button btn_voltarTela;
    public final ModuleMethod btn_voltarTela$Click;
    public LList components$Mnto$Mncreate;
    public TextBox cx_numero;
    public final ModuleMethod dispatchEvent;
    public final ModuleMethod dispatchGenericEvent;
    public LList events$Mnto$Mnregister;
    public LList form$Mndo$Mnafter$Mncreation;
    public Environment form$Mnenvironment;
    public Symbol form$Mnname$Mnsymbol;
    public final ModuleMethod get$Mnsimple$Mnname;
    public Environment global$Mnvar$Mnenvironment;
    public LList global$Mnvars$Mnto$Mncreate;
    public final ModuleMethod is$Mnbound$Mnin$Mnform$Mnenvironment;
    public final ModuleMethod lookup$Mnhandler;
    public final ModuleMethod lookup$Mnin$Mnform$Mnenvironment;
    public final ModuleMethod onCreate;
    public final ModuleMethod process$Mnexception;
    public final ModuleMethod recuperar_senha$Initialize;
    public final ModuleMethod send$Mnerror;
    public Texting sms_numero;
    public Label txt_Titulo;
    public Label txt_informarNumero;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_TEXT).readResolve();
        Lit6 = simpleSymbol;
        Lit91 = PairWithPosition.make(simpleSymbol, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/recuperar_senha.yail", 582213);
        int[] iArr = new int[2];
        iArr[0] = -1;
        Lit81 = IntNum.make(iArr);
        int[] iArr2 = new int[2];
        iArr2[0] = -10243728;
        Lit80 = IntNum.make(iArr2);
        int[] iArr3 = new int[2];
        iArr3[0] = -1;
        Lit64 = IntNum.make(iArr3);
        int[] iArr4 = new int[2];
        iArr4[0] = -14336;
        Lit59 = IntNum.make(iArr4);
        int[] iArr5 = new int[2];
        iArr5[0] = -12303292;
        Lit45 = IntNum.make(iArr5);
        int[] iArr6 = new int[2];
        iArr6[0] = -12303292;
        Lit8 = IntNum.make(iArr6);
    }

    public recuperar_senha() {
        ModuleInfo.register(this);
        frame frame2 = new frame();
        frame2.$main = this;
        this.get$Mnsimple$Mnname = new ModuleMethod(frame2, 1, Lit98, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.onCreate = new ModuleMethod(frame2, 2, "onCreate", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.android$Mnlog$Mnform = new ModuleMethod(frame2, 3, Lit99, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnform$Mnenvironment = new ModuleMethod(frame2, 4, Lit100, 8194);
        this.lookup$Mnin$Mnform$Mnenvironment = new ModuleMethod(frame2, 5, Lit101, 8193);
        this.is$Mnbound$Mnin$Mnform$Mnenvironment = new ModuleMethod(frame2, 7, Lit102, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = new ModuleMethod(frame2, 8, Lit103, 8194);
        this.add$Mnto$Mnevents = new ModuleMethod(frame2, 9, Lit104, 8194);
        this.add$Mnto$Mncomponents = new ModuleMethod(frame2, 10, Lit105, 16388);
        this.add$Mnto$Mnglobal$Mnvars = new ModuleMethod(frame2, 11, Lit106, 8194);
        this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = new ModuleMethod(frame2, 12, Lit107, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.send$Mnerror = new ModuleMethod(frame2, 13, Lit108, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.process$Mnexception = new ModuleMethod(frame2, 14, "process-exception", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.dispatchEvent = new ModuleMethod(frame2, 15, Lit109, 16388);
        this.dispatchGenericEvent = new ModuleMethod(frame2, 16, Lit110, 16388);
        this.lookup$Mnhandler = new ModuleMethod(frame2, 17, Lit111, 8194);
        ModuleMethod moduleMethod = new ModuleMethod(frame2, 18, (Object) null, 0);
        moduleMethod.setProperty("source-location", "/tmp/runtime6229131719407907284.scm:627");
        lambda$Fn1 = moduleMethod;
        this.$define = new ModuleMethod(frame2, 19, "$define", 0);
        lambda$Fn2 = new ModuleMethod(frame2, 20, (Object) null, 0);
        this.recuperar_senha$Initialize = new ModuleMethod(frame2, 21, Lit20, 0);
        lambda$Fn3 = new ModuleMethod(frame2, 22, (Object) null, 0);
        lambda$Fn4 = new ModuleMethod(frame2, 23, (Object) null, 0);
        lambda$Fn5 = new ModuleMethod(frame2, 24, (Object) null, 0);
        lambda$Fn6 = new ModuleMethod(frame2, 25, (Object) null, 0);
        this.btn_voltarTela$Click = new ModuleMethod(frame2, 26, Lit39, 0);
        lambda$Fn7 = new ModuleMethod(frame2, 27, (Object) null, 0);
        lambda$Fn8 = new ModuleMethod(frame2, 28, (Object) null, 0);
        lambda$Fn9 = new ModuleMethod(frame2, 29, (Object) null, 0);
        lambda$Fn10 = new ModuleMethod(frame2, 30, (Object) null, 0);
        lambda$Fn11 = new ModuleMethod(frame2, 31, (Object) null, 0);
        lambda$Fn12 = new ModuleMethod(frame2, 32, (Object) null, 0);
        lambda$Fn13 = new ModuleMethod(frame2, 33, (Object) null, 0);
        lambda$Fn14 = new ModuleMethod(frame2, 34, (Object) null, 0);
        lambda$Fn15 = new ModuleMethod(frame2, 35, (Object) null, 0);
        lambda$Fn16 = new ModuleMethod(frame2, 36, (Object) null, 0);
        lambda$Fn17 = new ModuleMethod(frame2, 37, (Object) null, 0);
        lambda$Fn18 = new ModuleMethod(frame2, 38, (Object) null, 0);
        lambda$Fn19 = new ModuleMethod(frame2, 39, (Object) null, 0);
        lambda$Fn20 = new ModuleMethod(frame2, 40, (Object) null, 0);
        lambda$Fn21 = new ModuleMethod(frame2, 41, (Object) null, 0);
        lambda$Fn22 = new ModuleMethod(frame2, 42, (Object) null, 0);
        this.btn_codigo$Click = new ModuleMethod(frame2, 43, Lit92, 0);
        lambda$Fn23 = new ModuleMethod(frame2, 44, (Object) null, 0);
        lambda$Fn24 = new ModuleMethod(frame2, 45, (Object) null, 0);
    }

    public Object lookupInFormEnvironment(Symbol symbol) {
        return lookupInFormEnvironment(symbol, Boolean.FALSE);
    }

    public void run() {
        CallContext instance = CallContext.getInstance();
        Consumer consumer = instance.consumer;
        instance.consumer = VoidConsumer.instance;
        try {
            run(instance);
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        ModuleBody.runCleanup(instance, th, consumer);
    }

    public final void run(CallContext $ctx) {
        String obj;
        Consumer $result = $ctx.consumer;
        Object find = require.find("com.google.youngandroid.runtime");
        try {
            ((Runnable) find).run();
            this.$Stdebug$Mnform$St = Boolean.FALSE;
            this.form$Mnenvironment = Environment.make(misc.symbol$To$String(Lit0));
            FString stringAppend = strings.stringAppend(misc.symbol$To$String(Lit0), "-global-vars");
            if (stringAppend == null) {
                obj = null;
            } else {
                obj = stringAppend.toString();
            }
            this.global$Mnvar$Mnenvironment = Environment.make(obj);
            recuperar_senha = null;
            this.form$Mnname$Mnsymbol = Lit0;
            this.events$Mnto$Mnregister = LList.Empty;
            this.components$Mnto$Mncreate = LList.Empty;
            this.global$Mnvars$Mnto$Mncreate = LList.Empty;
            this.form$Mndo$Mnafter$Mncreation = LList.Empty;
            Object find2 = require.find("com.google.youngandroid.runtime");
            try {
                ((Runnable) find2).run();
                if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit3, Boolean.TRUE, Lit4);
                    C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit5, "EVA_imobilizer", Lit6);
                    C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit7, Lit8, Lit9);
                    C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit10, "slidevertical", Lit6);
                    C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit11, "slidevertical", Lit6);
                    C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit12, "user", Lit6);
                    C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit13, Boolean.TRUE, Lit4);
                    C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit14, "Responsive", Lit6);
                    C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit15, "AppTheme", Lit6);
                    C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit16, "recuperar_senha", Lit6);
                    Values.writeValues(C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit17, Boolean.FALSE, Lit4), $result);
                } else {
                    addToFormDoAfterCreation(new Promise(lambda$Fn2));
                }
                if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    C0718runtime.addToCurrentFormEnvironment(Lit20, this.recuperar_senha$Initialize);
                } else {
                    addToFormEnvironment(Lit20, this.recuperar_senha$Initialize);
                }
                if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "recuperar_senha", "Initialize");
                } else {
                    addToEvents(Lit0, Lit21);
                }
                this.Legenda1 = null;
                if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit22, Lit23, lambda$Fn3), $result);
                } else {
                    addToComponents(Lit0, Lit28, Lit23, lambda$Fn4);
                }
                this.btn_voltarTela = null;
                if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit29, Lit30, lambda$Fn5), $result);
                } else {
                    addToComponents(Lit0, Lit37, Lit30, lambda$Fn6);
                }
                if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    C0718runtime.addToCurrentFormEnvironment(Lit39, this.btn_voltarTela$Click);
                } else {
                    addToFormEnvironment(Lit39, this.btn_voltarTela$Click);
                }
                if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "btn_voltarTela", "Click");
                } else {
                    addToEvents(Lit30, Lit40);
                }
                this.f439OrganizaoVertical1 = null;
                if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit41, Lit42, lambda$Fn7), $result);
                } else {
                    addToComponents(Lit0, Lit47, Lit42, lambda$Fn8);
                }
                this.f437OrganizaoHorizontal2 = null;
                if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C0718runtime.addComponentWithinRepl(Lit42, Lit48, Lit49, lambda$Fn9), $result);
                } else {
                    addToComponents(Lit42, Lit51, Lit49, lambda$Fn10);
                }
                this.txt_Titulo = null;
                if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C0718runtime.addComponentWithinRepl(Lit42, Lit52, Lit53, lambda$Fn11), $result);
                } else {
                    addToComponents(Lit42, Lit60, Lit53, lambda$Fn12);
                }
                this.txt_informarNumero = null;
                if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C0718runtime.addComponentWithinRepl(Lit42, Lit61, Lit18, lambda$Fn13), $result);
                } else {
                    addToComponents(Lit42, Lit65, Lit18, lambda$Fn14);
                }
                this.f438OrganizaoHorizontal3 = null;
                if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C0718runtime.addComponentWithinRepl(Lit42, Lit66, Lit67, lambda$Fn15), $result);
                } else {
                    addToComponents(Lit42, Lit69, Lit67, lambda$Fn16);
                }
                this.cx_numero = null;
                if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C0718runtime.addComponentWithinRepl(Lit42, Lit70, Lit71, lambda$Fn17), $result);
                } else {
                    addToComponents(Lit42, Lit74, Lit71, lambda$Fn18);
                }
                this.f436OrganizaoHorizontal1 = null;
                if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C0718runtime.addComponentWithinRepl(Lit42, Lit75, Lit76, lambda$Fn19), $result);
                } else {
                    addToComponents(Lit42, Lit77, Lit76, lambda$Fn20);
                }
                this.btn_codigo = null;
                if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C0718runtime.addComponentWithinRepl(Lit42, Lit78, Lit79, lambda$Fn21), $result);
                } else {
                    addToComponents(Lit42, Lit82, Lit79, lambda$Fn22);
                }
                if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    C0718runtime.addToCurrentFormEnvironment(Lit92, this.btn_codigo$Click);
                } else {
                    addToFormEnvironment(Lit92, this.btn_codigo$Click);
                }
                if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "btn_codigo", "Click");
                } else {
                    addToEvents(Lit79, Lit40);
                }
                this.Notificador1 = null;
                if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit93, Lit89, Boolean.FALSE), $result);
                } else {
                    addToComponents(Lit0, Lit94, Lit89, Boolean.FALSE);
                }
                this.sms_numero = null;
                if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit95, Lit84, lambda$Fn23), $result);
                } else {
                    addToComponents(Lit0, Lit97, Lit84, lambda$Fn24);
                }
                C0718runtime.initRuntime();
            } catch (ClassCastException e) {
                throw new WrongType(e, "java.lang.Runnable.run()", 1, find2);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "java.lang.Runnable.run()", 1, find);
        }
    }

    static Object lambda3() {
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit3, Boolean.TRUE, Lit4);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit5, "EVA_imobilizer", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit7, Lit8, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit10, "slidevertical", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit11, "slidevertical", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit12, "user", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit13, Boolean.TRUE, Lit4);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit14, "Responsive", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit15, "AppTheme", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit16, "recuperar_senha", Lit6);
        return C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit17, Boolean.FALSE, Lit4);
    }

    public Object recuperar_senha$Initialize() {
        C0718runtime.setThisForm();
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit10, "trocar_senha", Lit6);
        return C0718runtime.setAndCoerceProperty$Ex(Lit18, Lit19, Boolean.TRUE, Lit4);
    }

    static Object lambda4() {
        C0718runtime.setAndCoerceProperty$Ex(Lit23, Lit24, Lit25, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit23, Lit26, Lit27, Lit9);
    }

    static Object lambda5() {
        C0718runtime.setAndCoerceProperty$Ex(Lit23, Lit24, Lit25, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit23, Lit26, Lit27, Lit9);
    }

    static Object lambda6() {
        C0718runtime.setAndCoerceProperty$Ex(Lit30, Lit7, Lit31, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit30, Lit24, Lit32, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit30, Lit33, "setaCorreta-removebg-preview.png", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit30, Lit26, Lit34, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit30, Lit35, Lit36, Lit9);
    }

    static Object lambda7() {
        C0718runtime.setAndCoerceProperty$Ex(Lit30, Lit7, Lit31, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit30, Lit24, Lit32, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit30, Lit33, "setaCorreta-removebg-preview.png", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit30, Lit26, Lit34, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit30, Lit35, Lit36, Lit9);
    }

    public Object btn_voltarTela$Click() {
        C0718runtime.setThisForm();
        return C0718runtime.callYailPrimitive(C0718runtime.open$Mnanother$Mnscreen, LList.list1("Screen1"), Lit38, "open another screen");
    }

    static Object lambda8() {
        C0718runtime.setAndCoerceProperty$Ex(Lit42, Lit43, Lit44, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit42, Lit7, Lit45, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit42, Lit24, Lit46, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit42, Lit35, Lit46, Lit9);
    }

    static Object lambda9() {
        C0718runtime.setAndCoerceProperty$Ex(Lit42, Lit43, Lit44, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit42, Lit7, Lit45, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit42, Lit24, Lit46, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit42, Lit35, Lit46, Lit9);
    }

    static Object lambda10() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit49, Lit24, Lit50, Lit9);
    }

    static Object lambda11() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit49, Lit24, Lit50, Lit9);
    }

    static Object lambda12() {
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit54, Lit55, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit56, "Preencha os campos", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit57, Lit58, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit26, Lit59, Lit9);
    }

    static Object lambda13() {
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit54, Lit55, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit56, "Preencha os campos", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit57, Lit58, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit26, Lit59, Lit9);
    }

    static Object lambda14() {
        C0718runtime.setAndCoerceProperty$Ex(Lit18, Lit54, Lit62, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit18, Lit24, Lit63, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit18, Lit56, "Número do telefone em que será enviado o código de alteração de senha:", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit18, Lit26, Lit64, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit18, Lit35, Lit46, Lit9);
    }

    static Object lambda15() {
        C0718runtime.setAndCoerceProperty$Ex(Lit18, Lit54, Lit62, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit18, Lit24, Lit63, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit18, Lit56, "Número do telefone em que será enviado o código de alteração de senha:", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit18, Lit26, Lit64, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit18, Lit35, Lit46, Lit9);
    }

    static Object lambda16() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit67, Lit24, Lit68, Lit9);
    }

    static Object lambda17() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit67, Lit24, Lit68, Lit9);
    }

    static Object lambda18() {
        C0718runtime.setAndCoerceProperty$Ex(Lit71, Lit54, Lit50, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit71, Lit24, Lit72, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit71, Lit73, "Ex: (11) 91234-5678", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit71, Lit57, Lit58, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit71, Lit35, Lit46, Lit9);
    }

    static Object lambda19() {
        C0718runtime.setAndCoerceProperty$Ex(Lit71, Lit54, Lit50, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit71, Lit24, Lit72, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit71, Lit73, "Ex: (11) 91234-5678", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit71, Lit57, Lit58, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit71, Lit35, Lit46, Lit9);
    }

    static Object lambda20() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit76, Lit24, Lit50, Lit9);
    }

    static Object lambda21() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit76, Lit24, Lit50, Lit9);
    }

    static Object lambda22() {
        C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit7, Lit80, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit54, Lit50, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit56, "Enviar", Lit6);
        return C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit26, Lit81, Lit9);
    }

    static Object lambda23() {
        C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit7, Lit80, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit54, Lit50, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit56, "Enviar", Lit6);
        return C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit26, Lit81, Lit9);
    }

    public Object btn_codigo$Click() {
        C0718runtime.setThisForm();
        if (C0718runtime.callYailPrimitive(strings.string$Eq$Qu, LList.list2(C0718runtime.getProperty$1(Lit71, Lit56), ""), Lit83, "not =") == Boolean.FALSE) {
            C0718runtime.setAndCoerceProperty$Ex(Lit84, Lit85, "1234", Lit6);
            C0718runtime.setAndCoerceProperty$Ex(Lit84, Lit86, C0718runtime.getProperty$1(Lit71, Lit56), Lit6);
            C0718runtime.callComponentMethod(Lit84, Lit87, LList.Empty, LList.Empty);
            return C0718runtime.callYailPrimitive(C0718runtime.open$Mnanother$Mnscreen, LList.list1("trocar_senha"), Lit88, "open another screen");
        }
        C0718runtime.callComponentMethod(Lit89, Lit90, LList.list1("Número não informado"), Lit91);
        return C0718runtime.setAndCoerceProperty$Ex(Lit71, Lit56, "", Lit6);
    }

    static Object lambda24() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit84, Lit96, Lit58, Lit9);
    }

    static Object lambda25() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit84, Lit96, Lit58, Lit9);
    }

    /* compiled from: recuperar_senha.yail */
    public class frame extends ModuleBody {
        recuperar_senha $main;

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 1:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 2:
                    if (!(obj instanceof recuperar_senha)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 3:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 5:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 7:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 12:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 13:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 14:
                    if (!(obj instanceof recuperar_senha)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                default:
                    return super.match1(moduleMethod, obj, callContext);
            }
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 4:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 2;
                    return 0;
                case 5:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 2;
                    return 0;
                case 8:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 2;
                    return 0;
                case 9:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 2;
                    return 0;
                case 11:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 2;
                    return 0;
                case 17:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 2;
                    return 0;
                default:
                    return super.match2(moduleMethod, obj, obj2, callContext);
            }
        }

        public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 10:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.value4 = obj4;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 4;
                    return 0;
                case 15:
                    if (!(obj instanceof recuperar_senha)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    if (!(obj2 instanceof Component)) {
                        return -786430;
                    }
                    callContext.value2 = obj2;
                    if (!(obj3 instanceof String)) {
                        return -786429;
                    }
                    callContext.value3 = obj3;
                    if (!(obj4 instanceof String)) {
                        return -786428;
                    }
                    callContext.value4 = obj4;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 4;
                    return 0;
                case 16:
                    if (!(obj instanceof recuperar_senha)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    if (!(obj2 instanceof Component)) {
                        return -786430;
                    }
                    callContext.value2 = obj2;
                    if (!(obj3 instanceof String)) {
                        return -786429;
                    }
                    callContext.value3 = obj3;
                    callContext.value4 = obj4;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 4;
                    return 0;
                default:
                    return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
            }
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            switch (moduleMethod.selector) {
                case 1:
                    return this.$main.getSimpleName(obj);
                case 2:
                    try {
                        this.$main.onCreate((Bundle) obj);
                        return Values.empty;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "onCreate", 1, obj);
                    }
                case 3:
                    this.$main.androidLogForm(obj);
                    return Values.empty;
                case 5:
                    try {
                        return this.$main.lookupInFormEnvironment((Symbol) obj);
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "lookup-in-form-environment", 1, obj);
                    }
                case 7:
                    try {
                        return this.$main.isBoundInFormEnvironment((Symbol) obj) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "is-bound-in-form-environment", 1, obj);
                    }
                case 12:
                    this.$main.addToFormDoAfterCreation(obj);
                    return Values.empty;
                case 13:
                    this.$main.sendError(obj);
                    return Values.empty;
                case 14:
                    this.$main.processException(obj);
                    return Values.empty;
                default:
                    return super.apply1(moduleMethod, obj);
            }
        }

        public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
            boolean z = true;
            switch (moduleMethod.selector) {
                case 10:
                    this.$main.addToComponents(obj, obj2, obj3, obj4);
                    return Values.empty;
                case 15:
                    try {
                        try {
                            try {
                                try {
                                    return this.$main.dispatchEvent((Component) obj, (String) obj2, (String) obj3, (Object[]) obj4) ? Boolean.TRUE : Boolean.FALSE;
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "dispatchEvent", 4, obj4);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "dispatchEvent", 3, obj3);
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "dispatchEvent", 2, obj2);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "dispatchEvent", 1, obj);
                    }
                case 16:
                    recuperar_senha recuperar_senha = this.$main;
                    try {
                        Component component = (Component) obj;
                        try {
                            String str = (String) obj2;
                            try {
                                if (obj3 == Boolean.FALSE) {
                                    z = false;
                                }
                                try {
                                    recuperar_senha.dispatchGenericEvent(component, str, z, (Object[]) obj4);
                                    return Values.empty;
                                } catch (ClassCastException e5) {
                                    throw new WrongType(e5, "dispatchGenericEvent", 4, obj4);
                                }
                            } catch (ClassCastException e6) {
                                throw new WrongType(e6, "dispatchGenericEvent", 3, obj3);
                            }
                        } catch (ClassCastException e7) {
                            throw new WrongType(e7, "dispatchGenericEvent", 2, obj2);
                        }
                    } catch (ClassCastException e8) {
                        throw new WrongType(e8, "dispatchGenericEvent", 1, obj);
                    }
                default:
                    return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
            }
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            switch (moduleMethod.selector) {
                case 4:
                    try {
                        this.$main.addToFormEnvironment((Symbol) obj, obj2);
                        return Values.empty;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "add-to-form-environment", 1, obj);
                    }
                case 5:
                    try {
                        return this.$main.lookupInFormEnvironment((Symbol) obj, obj2);
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "lookup-in-form-environment", 1, obj);
                    }
                case 8:
                    try {
                        this.$main.addToGlobalVarEnvironment((Symbol) obj, obj2);
                        return Values.empty;
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "add-to-global-var-environment", 1, obj);
                    }
                case 9:
                    this.$main.addToEvents(obj, obj2);
                    return Values.empty;
                case 11:
                    this.$main.addToGlobalVars(obj, obj2);
                    return Values.empty;
                case 17:
                    return this.$main.lookupHandler(obj, obj2);
                default:
                    return super.apply2(moduleMethod, obj, obj2);
            }
        }

        public Object apply0(ModuleMethod moduleMethod) {
            switch (moduleMethod.selector) {
                case 18:
                    return recuperar_senha.lambda2();
                case 19:
                    this.$main.$define();
                    return Values.empty;
                case 20:
                    return recuperar_senha.lambda3();
                case 21:
                    return this.$main.recuperar_senha$Initialize();
                case 22:
                    return recuperar_senha.lambda4();
                case 23:
                    return recuperar_senha.lambda5();
                case 24:
                    return recuperar_senha.lambda6();
                case 25:
                    return recuperar_senha.lambda7();
                case 26:
                    return this.$main.btn_voltarTela$Click();
                case 27:
                    return recuperar_senha.lambda8();
                case 28:
                    return recuperar_senha.lambda9();
                case 29:
                    return recuperar_senha.lambda10();
                case 30:
                    return recuperar_senha.lambda11();
                case 31:
                    return recuperar_senha.lambda12();
                case 32:
                    return recuperar_senha.lambda13();
                case 33:
                    return recuperar_senha.lambda14();
                case 34:
                    return recuperar_senha.lambda15();
                case 35:
                    return recuperar_senha.lambda16();
                case 36:
                    return recuperar_senha.lambda17();
                case 37:
                    return recuperar_senha.lambda18();
                case 38:
                    return recuperar_senha.lambda19();
                case 39:
                    return recuperar_senha.lambda20();
                case 40:
                    return recuperar_senha.lambda21();
                case 41:
                    return recuperar_senha.lambda22();
                case 42:
                    return recuperar_senha.lambda23();
                case 43:
                    return this.$main.btn_codigo$Click();
                case 44:
                    return recuperar_senha.lambda24();
                case 45:
                    return recuperar_senha.lambda25();
                default:
                    return super.apply0(moduleMethod);
            }
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 18:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 19:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 20:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 21:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 22:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 23:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 24:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 25:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 26:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 27:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 28:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 29:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 30:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 31:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 32:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 33:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 34:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 35:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 36:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 37:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 38:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 39:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 40:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 41:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 42:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 43:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 44:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 45:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                default:
                    return super.match0(moduleMethod, callContext);
            }
        }
    }

    public String getSimpleName(Object object) {
        return object.getClass().getSimpleName();
    }

    public void onCreate(Bundle icicle) {
        AppInventorCompatActivity.setClassicModeFromYail(false);
        super.onCreate(icicle);
    }

    public void androidLogForm(Object message) {
    }

    public void addToFormEnvironment(Symbol name, Object object) {
        androidLogForm(Format.formatToString(0, "Adding ~A to env ~A with value ~A", name, this.form$Mnenvironment, object));
        this.form$Mnenvironment.put(name, object);
    }

    public Object lookupInFormEnvironment(Symbol name, Object default$Mnvalue) {
        boolean x = ((this.form$Mnenvironment == null ? 1 : 0) + 1) & true;
        if (x) {
            if (!this.form$Mnenvironment.isBound(name)) {
                return default$Mnvalue;
            }
        } else if (!x) {
            return default$Mnvalue;
        }
        return this.form$Mnenvironment.get(name);
    }

    public boolean isBoundInFormEnvironment(Symbol name) {
        return this.form$Mnenvironment.isBound(name);
    }

    public void addToGlobalVarEnvironment(Symbol name, Object object) {
        androidLogForm(Format.formatToString(0, "Adding ~A to env ~A with value ~A", name, this.global$Mnvar$Mnenvironment, object));
        this.global$Mnvar$Mnenvironment.put(name, object);
    }

    public void addToEvents(Object component$Mnname, Object event$Mnname) {
        this.events$Mnto$Mnregister = C0731lists.cons(C0731lists.cons(component$Mnname, event$Mnname), this.events$Mnto$Mnregister);
    }

    public void addToComponents(Object container$Mnname, Object component$Mntype, Object component$Mnname, Object init$Mnthunk) {
        this.components$Mnto$Mncreate = C0731lists.cons(LList.list4(container$Mnname, component$Mntype, component$Mnname, init$Mnthunk), this.components$Mnto$Mncreate);
    }

    public void addToGlobalVars(Object var, Object val$Mnthunk) {
        this.global$Mnvars$Mnto$Mncreate = C0731lists.cons(LList.list2(var, val$Mnthunk), this.global$Mnvars$Mnto$Mncreate);
    }

    public void addToFormDoAfterCreation(Object thunk) {
        this.form$Mndo$Mnafter$Mncreation = C0731lists.cons(thunk, this.form$Mndo$Mnafter$Mncreation);
    }

    public void sendError(Object error) {
        RetValManager.sendError(error == null ? null : error.toString());
    }

    public void processException(Object ex) {
        Object apply1 = Scheme.applyToArgs.apply1(GetNamedPart.getNamedPart.apply2(ex, Lit1));
        RuntimeErrorAlert.alert(this, apply1 == null ? null : apply1.toString(), ex instanceof YailRuntimeError ? ((YailRuntimeError) ex).getErrorType() : "Runtime Error", "End Application");
    }

    public boolean dispatchEvent(Component componentObject, String registeredComponentName, String eventName, Object[] args) {
        boolean x;
        SimpleSymbol registeredObject = misc.string$To$Symbol(registeredComponentName);
        if (!isBoundInFormEnvironment(registeredObject)) {
            EventDispatcher.unregisterEventForDelegation(this, registeredComponentName, eventName);
            return false;
        } else if (lookupInFormEnvironment(registeredObject) != componentObject) {
            return false;
        } else {
            try {
                Scheme.apply.apply2(lookupHandler(registeredComponentName, eventName), LList.makeList(args, 0));
                return true;
            } catch (StopBlocksExecution e) {
                return false;
            } catch (PermissionException exception) {
                exception.printStackTrace();
                if (this == componentObject) {
                    x = true;
                } else {
                    x = false;
                }
                if (!x ? x : IsEqual.apply(eventName, "PermissionNeeded")) {
                    processException(exception);
                } else {
                    PermissionDenied(componentObject, eventName, exception.getPermissionNeeded());
                }
                return false;
            } catch (Throwable exception2) {
                androidLogForm(exception2.getMessage());
                exception2.printStackTrace();
                processException(exception2);
                return false;
            }
        }
    }

    public void dispatchGenericEvent(Component componentObject, String eventName, boolean notAlreadyHandled, Object[] args) {
        Boolean bool;
        boolean x = true;
        Object handler = lookupInFormEnvironment(misc.string$To$Symbol(strings.stringAppend("any$", getSimpleName(componentObject), "$", eventName)));
        if (handler != Boolean.FALSE) {
            try {
                Apply apply = Scheme.apply;
                if (notAlreadyHandled) {
                    bool = Boolean.TRUE;
                } else {
                    bool = Boolean.FALSE;
                }
                apply.apply2(handler, C0731lists.cons(componentObject, C0731lists.cons(bool, LList.makeList(args, 0))));
            } catch (StopBlocksExecution e) {
            } catch (PermissionException exception) {
                exception.printStackTrace();
                if (this != componentObject) {
                    x = false;
                }
                if (!x ? x : IsEqual.apply(eventName, "PermissionNeeded")) {
                    processException(exception);
                } else {
                    PermissionDenied(componentObject, eventName, exception.getPermissionNeeded());
                }
            } catch (Throwable exception2) {
                androidLogForm(exception2.getMessage());
                exception2.printStackTrace();
                processException(exception2);
            }
        }
    }

    public Object lookupHandler(Object componentName, Object eventName) {
        String str = null;
        String obj = componentName == null ? null : componentName.toString();
        if (eventName != null) {
            str = eventName.toString();
        }
        return lookupInFormEnvironment(misc.string$To$Symbol(EventDispatcher.makeFullEventName(obj, str)));
    }

    public void $define() {
        Object reverse;
        Object obj;
        Object reverse2;
        Object obj2;
        Object obj3;
        Object var;
        Object component$Mnname;
        Object obj4;
        Language.setDefaults(Scheme.getInstance());
        try {
            run();
        } catch (Exception exception) {
            androidLogForm(exception.getMessage());
            processException(exception);
        }
        recuperar_senha = this;
        addToFormEnvironment(Lit0, this);
        Object obj5 = this.events$Mnto$Mnregister;
        while (obj5 != LList.Empty) {
            try {
                Pair arg0 = (Pair) obj5;
                Object event$Mninfo = arg0.getCar();
                Object apply1 = C0731lists.car.apply1(event$Mninfo);
                String obj6 = apply1 == null ? null : apply1.toString();
                Object apply12 = C0731lists.cdr.apply1(event$Mninfo);
                EventDispatcher.registerEventForDelegation(this, obj6, apply12 == null ? null : apply12.toString());
                obj5 = arg0.getCdr();
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, obj5);
            }
        }
        try {
            LList components = C0731lists.reverse(this.components$Mnto$Mncreate);
            addToGlobalVars(Lit2, lambda$Fn1);
            reverse = C0731lists.reverse(this.form$Mndo$Mnafter$Mncreation);
            while (reverse != LList.Empty) {
                Pair arg02 = (Pair) reverse;
                misc.force(arg02.getCar());
                reverse = arg02.getCdr();
            }
            obj = components;
            while (obj != LList.Empty) {
                Pair arg03 = (Pair) obj;
                Object component$Mninfo = arg03.getCar();
                component$Mnname = C0731lists.caddr.apply1(component$Mninfo);
                C0731lists.cadddr.apply1(component$Mninfo);
                Object component$Mnobject = Invoke.make.apply2(C0731lists.cadr.apply1(component$Mninfo), lookupInFormEnvironment((Symbol) C0731lists.car.apply1(component$Mninfo)));
                SlotSet.set$Mnfield$Ex.apply3(this, component$Mnname, component$Mnobject);
                addToFormEnvironment((Symbol) component$Mnname, component$Mnobject);
                obj = arg03.getCdr();
            }
            reverse2 = C0731lists.reverse(this.global$Mnvars$Mnto$Mncreate);
            while (reverse2 != LList.Empty) {
                Pair arg04 = (Pair) reverse2;
                Object var$Mnval = arg04.getCar();
                var = C0731lists.car.apply1(var$Mnval);
                addToGlobalVarEnvironment((Symbol) var, Scheme.applyToArgs.apply1(C0731lists.cadr.apply1(var$Mnval)));
                reverse2 = arg04.getCdr();
            }
            LList component$Mndescriptors = components;
            obj2 = component$Mndescriptors;
            while (obj2 != LList.Empty) {
                Pair arg05 = (Pair) obj2;
                Object component$Mninfo2 = arg05.getCar();
                C0731lists.caddr.apply1(component$Mninfo2);
                Object init$Mnthunk = C0731lists.cadddr.apply1(component$Mninfo2);
                if (init$Mnthunk != Boolean.FALSE) {
                    Scheme.applyToArgs.apply1(init$Mnthunk);
                }
                obj2 = arg05.getCdr();
            }
            obj3 = component$Mndescriptors;
            while (obj3 != LList.Empty) {
                Pair arg06 = (Pair) obj3;
                Object component$Mninfo3 = arg06.getCar();
                Object component$Mnname2 = C0731lists.caddr.apply1(component$Mninfo3);
                C0731lists.cadddr.apply1(component$Mninfo3);
                callInitialize(SlotGet.field.apply2(this, component$Mnname2));
                obj3 = arg06.getCdr();
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "arg0", -2, obj3);
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "arg0", -2, obj2);
        } catch (ClassCastException e4) {
            throw new WrongType(e4, "add-to-global-var-environment", 0, var);
        } catch (ClassCastException e5) {
            throw new WrongType(e5, "arg0", -2, reverse2);
        } catch (ClassCastException e6) {
            throw new WrongType(e6, "add-to-form-environment", 0, component$Mnname);
        } catch (ClassCastException e7) {
            throw new WrongType(e7, "lookup-in-form-environment", 0, obj4);
        } catch (ClassCastException e8) {
            throw new WrongType(e8, "arg0", -2, obj);
        } catch (ClassCastException e9) {
            throw new WrongType(e9, "arg0", -2, reverse);
        } catch (YailRuntimeError exception2) {
            processException(exception2);
        }
    }

    public static SimpleSymbol lambda1symbolAppend$V(Object[] argsArray) {
        LList symbols = LList.makeList(argsArray, 0);
        Apply apply = Scheme.apply;
        ModuleMethod moduleMethod = strings.string$Mnappend;
        Object obj = LList.Empty;
        LList lList = symbols;
        while (lList != LList.Empty) {
            try {
                Pair arg0 = (Pair) lList;
                Object arg02 = arg0.getCdr();
                Object car = arg0.getCar();
                try {
                    obj = Pair.make(misc.symbol$To$String((Symbol) car), obj);
                    lList = arg02;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "symbol->string", 1, car);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "arg0", -2, lList);
            }
        }
        Object apply2 = apply.apply2(moduleMethod, LList.reverseInPlace(obj));
        try {
            return misc.string$To$Symbol((CharSequence) apply2);
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "string->symbol", 1, apply2);
        }
    }

    static Object lambda2() {
        return null;
    }
}
