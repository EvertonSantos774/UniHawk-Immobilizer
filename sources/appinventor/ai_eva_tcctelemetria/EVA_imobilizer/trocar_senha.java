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
import com.google.appinventor.components.runtime.PasswordTextBox;
import com.google.appinventor.components.runtime.TextBox;
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

/* compiled from: trocar_senha.yail */
public class trocar_senha extends Form implements Runnable {
    static final SimpleSymbol Lit0 = ((SimpleSymbol) new SimpleSymbol("trocar_senha").readResolve());
    static final SimpleSymbol Lit1 = ((SimpleSymbol) new SimpleSymbol("getMessage").readResolve());
    static final SimpleSymbol Lit10 = ((SimpleSymbol) new SimpleSymbol("CloseScreenAnimation").readResolve());
    static final SimpleSymbol Lit100 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal7").readResolve());
    static final FString Lit101 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit102 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit103 = ((SimpleSymbol) new SimpleSymbol("txt_confirmarSenha").readResolve());
    static final IntNum Lit104 = IntNum.make(-1007);
    static final IntNum Lit105;
    static final FString Lit106 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit107 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit108 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal10").readResolve());
    static final FString Lit109 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit11 = ((SimpleSymbol) new SimpleSymbol("OpenScreenAnimation").readResolve());
    static final FString Lit110 = new FString("com.google.appinventor.components.runtime.PasswordTextBox");
    static final SimpleSymbol Lit111 = ((SimpleSymbol) new SimpleSymbol("cx_confirmarSenha").readResolve());
    static final IntNum Lit112 = IntNum.make(-1010);
    static final FString Lit113 = new FString("com.google.appinventor.components.runtime.PasswordTextBox");
    static final FString Lit114 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit115 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal4").readResolve());
    static final IntNum Lit116 = IntNum.make(45);
    static final FString Lit117 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit118 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit119 = ((SimpleSymbol) new SimpleSymbol("btn_enviar").readResolve());
    static final SimpleSymbol Lit12 = ((SimpleSymbol) new SimpleSymbol("ScreenOrientation").readResolve());
    static final IntNum Lit120;
    static final IntNum Lit121;
    static final FString Lit122 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit123 = PairWithPosition.make(Lit6, PairWithPosition.make(Lit6, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/trocar_senha.yail", 1060969), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/trocar_senha.yail", 1060963);
    static final PairWithPosition Lit124 = PairWithPosition.make(Lit6, PairWithPosition.make(Lit6, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/trocar_senha.yail", 1061106), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/trocar_senha.yail", 1061100);
    static final PairWithPosition Lit125 = PairWithPosition.make(Lit6, PairWithPosition.make(Lit6, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/trocar_senha.yail", 1061251), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/trocar_senha.yail", 1061245);
    static final PairWithPosition Lit126 = PairWithPosition.make(Lit6, PairWithPosition.make(Lit6, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/trocar_senha.yail", 1061368), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/trocar_senha.yail", 1061362);
    static final PairWithPosition Lit127 = PairWithPosition.make(Lit6, PairWithPosition.make(Lit6, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/trocar_senha.yail", 1061489), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/trocar_senha.yail", 1061483);
    static final PairWithPosition Lit128 = PairWithPosition.make(Lit6, PairWithPosition.make(Lit6, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/trocar_senha.yail", 1061606), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/trocar_senha.yail", 1061600);
    static final PairWithPosition Lit129 = PairWithPosition.make(Lit6, PairWithPosition.make(Lit6, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/trocar_senha.yail", 1061769), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/trocar_senha.yail", 1061763);
    static final SimpleSymbol Lit13 = ((SimpleSymbol) new SimpleSymbol("Scrollable").readResolve());
    static final PairWithPosition Lit130 = PairWithPosition.make(Lit6, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/trocar_senha.yail", 1062084);
    static final SimpleSymbol Lit131 = ((SimpleSymbol) new SimpleSymbol("Notificador1").readResolve());
    static final SimpleSymbol Lit132 = ((SimpleSymbol) new SimpleSymbol("ShowAlert").readResolve());
    static final PairWithPosition Lit133 = PairWithPosition.make(Lit6, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/trocar_senha.yail", 1062195);
    static final PairWithPosition Lit134 = PairWithPosition.make(Lit6, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/trocar_senha.yail", 1062422);
    static final PairWithPosition Lit135 = PairWithPosition.make(Lit6, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/trocar_senha.yail", 1062645);
    static final PairWithPosition Lit136;
    static final SimpleSymbol Lit137 = ((SimpleSymbol) new SimpleSymbol("btn_enviar$Click").readResolve());
    static final FString Lit138 = new FString("com.google.appinventor.components.runtime.Notifier");
    static final FString Lit139 = new FString("com.google.appinventor.components.runtime.Notifier");
    static final SimpleSymbol Lit14 = ((SimpleSymbol) new SimpleSymbol("ShowListsAsJson").readResolve());
    static final SimpleSymbol Lit140 = ((SimpleSymbol) new SimpleSymbol("get-simple-name").readResolve());
    static final SimpleSymbol Lit141 = ((SimpleSymbol) new SimpleSymbol("android-log-form").readResolve());
    static final SimpleSymbol Lit142 = ((SimpleSymbol) new SimpleSymbol("add-to-form-environment").readResolve());
    static final SimpleSymbol Lit143 = ((SimpleSymbol) new SimpleSymbol("lookup-in-form-environment").readResolve());
    static final SimpleSymbol Lit144 = ((SimpleSymbol) new SimpleSymbol("is-bound-in-form-environment").readResolve());
    static final SimpleSymbol Lit145 = ((SimpleSymbol) new SimpleSymbol("add-to-global-var-environment").readResolve());
    static final SimpleSymbol Lit146 = ((SimpleSymbol) new SimpleSymbol("add-to-events").readResolve());
    static final SimpleSymbol Lit147 = ((SimpleSymbol) new SimpleSymbol("add-to-components").readResolve());
    static final SimpleSymbol Lit148 = ((SimpleSymbol) new SimpleSymbol("add-to-global-vars").readResolve());
    static final SimpleSymbol Lit149 = ((SimpleSymbol) new SimpleSymbol("add-to-form-do-after-creation").readResolve());
    static final SimpleSymbol Lit15 = ((SimpleSymbol) new SimpleSymbol("Sizing").readResolve());
    static final SimpleSymbol Lit150 = ((SimpleSymbol) new SimpleSymbol("send-error").readResolve());
    static final SimpleSymbol Lit151 = ((SimpleSymbol) new SimpleSymbol("dispatchEvent").readResolve());
    static final SimpleSymbol Lit152 = ((SimpleSymbol) new SimpleSymbol("dispatchGenericEvent").readResolve());
    static final SimpleSymbol Lit153 = ((SimpleSymbol) new SimpleSymbol("lookup-handler").readResolve());
    static final SimpleSymbol Lit16 = ((SimpleSymbol) new SimpleSymbol("Theme").readResolve());
    static final SimpleSymbol Lit17 = ((SimpleSymbol) new SimpleSymbol("Title").readResolve());
    static final SimpleSymbol Lit18 = ((SimpleSymbol) new SimpleSymbol("TitleVisible").readResolve());
    static final FString Lit19 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit2 = ((SimpleSymbol) new SimpleSymbol("*the-null-value*").readResolve());
    static final SimpleSymbol Lit20 = ((SimpleSymbol) new SimpleSymbol("Legenda1").readResolve());
    static final SimpleSymbol Lit21 = ((SimpleSymbol) new SimpleSymbol("Height").readResolve());
    static final IntNum Lit22 = IntNum.make(-1001);
    static final SimpleSymbol Lit23 = ((SimpleSymbol) new SimpleSymbol("TextColor").readResolve());
    static final IntNum Lit24 = IntNum.make(16777215);
    static final FString Lit25 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit26 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit27 = ((SimpleSymbol) new SimpleSymbol("btn_voltarTela").readResolve());
    static final IntNum Lit28 = IntNum.make(16777215);
    static final IntNum Lit29 = IntNum.make(-1005);
    static final SimpleSymbol Lit3 = ((SimpleSymbol) new SimpleSymbol("ActionBar").readResolve());
    static final SimpleSymbol Lit30 = ((SimpleSymbol) new SimpleSymbol(Component.LISTVIEW_KEY_IMAGE).readResolve());
    static final IntNum Lit31 = IntNum.make(16777215);
    static final SimpleSymbol Lit32 = ((SimpleSymbol) new SimpleSymbol("Width").readResolve());
    static final IntNum Lit33 = IntNum.make(-1010);
    static final FString Lit34 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit35 = PairWithPosition.make(Lit6, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/trocar_senha.yail", 200790);
    static final SimpleSymbol Lit36 = ((SimpleSymbol) new SimpleSymbol("btn_voltarTela$Click").readResolve());
    static final SimpleSymbol Lit37 = ((SimpleSymbol) new SimpleSymbol("Click").readResolve());
    static final FString Lit38 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    static final SimpleSymbol Lit39 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoVertical1").readResolve());
    static final SimpleSymbol Lit4 = ((SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN).readResolve());
    static final SimpleSymbol Lit40 = ((SimpleSymbol) new SimpleSymbol("AlignHorizontal").readResolve());
    static final IntNum Lit41 = IntNum.make(3);
    static final IntNum Lit42 = IntNum.make(16777215);
    static final IntNum Lit43 = IntNum.make(-2);
    static final FString Lit44 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    static final FString Lit45 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit46 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal2").readResolve());
    static final SimpleSymbol Lit47 = ((SimpleSymbol) new SimpleSymbol("AlignVertical").readResolve());
    static final IntNum Lit48 = IntNum.make(2);
    static final IntNum Lit49 = IntNum.make(16777215);
    static final SimpleSymbol Lit5 = ((SimpleSymbol) new SimpleSymbol("AppName").readResolve());
    static final IntNum Lit50 = IntNum.make(50);
    static final FString Lit51 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit52 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit53 = ((SimpleSymbol) new SimpleSymbol("txt_codigo").readResolve());
    static final SimpleSymbol Lit54 = ((SimpleSymbol) new SimpleSymbol("FontSize").readResolve());
    static final IntNum Lit55 = IntNum.make(20);
    static final IntNum Lit56 = IntNum.make(25);
    static final SimpleSymbol Lit57 = ((SimpleSymbol) new SimpleSymbol("Text").readResolve());
    static final IntNum Lit58;
    static final FString Lit59 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit6;
    static final FString Lit60 = new FString("com.google.appinventor.components.runtime.PasswordTextBox");
    static final SimpleSymbol Lit61 = ((SimpleSymbol) new SimpleSymbol("cx_codigo").readResolve());
    static final IntNum Lit62 = IntNum.make(15);
    static final IntNum Lit63 = IntNum.make(-1010);
    static final SimpleSymbol Lit64 = ((SimpleSymbol) new SimpleSymbol("Hint").readResolve());
    static final SimpleSymbol Lit65 = ((SimpleSymbol) new SimpleSymbol("TextAlignment").readResolve());
    static final IntNum Lit66 = IntNum.make(1);
    static final FString Lit67 = new FString("com.google.appinventor.components.runtime.PasswordTextBox");
    static final FString Lit68 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit69 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal5").readResolve());
    static final SimpleSymbol Lit7 = ((SimpleSymbol) new SimpleSymbol("BackgroundColor").readResolve());
    static final FString Lit70 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit71 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit72 = ((SimpleSymbol) new SimpleSymbol("txt_email").readResolve());
    static final IntNum Lit73 = IntNum.make(-1007);
    static final IntNum Lit74;
    static final FString Lit75 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit76 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit77 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal8").readResolve());
    static final IntNum Lit78 = IntNum.make(5);
    static final FString Lit79 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final IntNum Lit8;
    static final FString Lit80 = new FString("com.google.appinventor.components.runtime.TextBox");
    static final SimpleSymbol Lit81 = ((SimpleSymbol) new SimpleSymbol("cx_email").readResolve());
    static final IntNum Lit82 = IntNum.make(-1010);
    static final FString Lit83 = new FString("com.google.appinventor.components.runtime.TextBox");
    static final FString Lit84 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit85 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal6").readResolve());
    static final FString Lit86 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit87 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit88 = ((SimpleSymbol) new SimpleSymbol("txt_nova_senha").readResolve());
    static final IntNum Lit89 = IntNum.make(-1007);
    static final SimpleSymbol Lit9 = ((SimpleSymbol) new SimpleSymbol("number").readResolve());
    static final IntNum Lit90;
    static final FString Lit91 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit92 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit93 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal9").readResolve());
    static final FString Lit94 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit95 = new FString("com.google.appinventor.components.runtime.PasswordTextBox");
    static final SimpleSymbol Lit96 = ((SimpleSymbol) new SimpleSymbol("cx_novaSenha").readResolve());
    static final IntNum Lit97 = IntNum.make(-1010);
    static final FString Lit98 = new FString("com.google.appinventor.components.runtime.PasswordTextBox");
    static final FString Lit99 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
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
    static final ModuleMethod lambda$Fn25 = null;
    static final ModuleMethod lambda$Fn26 = null;
    static final ModuleMethod lambda$Fn27 = null;
    static final ModuleMethod lambda$Fn28 = null;
    static final ModuleMethod lambda$Fn29 = null;
    static final ModuleMethod lambda$Fn3 = null;
    static final ModuleMethod lambda$Fn30 = null;
    static final ModuleMethod lambda$Fn31 = null;
    static final ModuleMethod lambda$Fn32 = null;
    static final ModuleMethod lambda$Fn33 = null;
    static final ModuleMethod lambda$Fn34 = null;
    static final ModuleMethod lambda$Fn35 = null;
    static final ModuleMethod lambda$Fn36 = null;
    static final ModuleMethod lambda$Fn37 = null;
    static final ModuleMethod lambda$Fn38 = null;
    static final ModuleMethod lambda$Fn39 = null;
    static final ModuleMethod lambda$Fn4 = null;
    static final ModuleMethod lambda$Fn40 = null;
    static final ModuleMethod lambda$Fn41 = null;
    static final ModuleMethod lambda$Fn42 = null;
    static final ModuleMethod lambda$Fn43 = null;
    static final ModuleMethod lambda$Fn44 = null;
    static final ModuleMethod lambda$Fn5 = null;
    static final ModuleMethod lambda$Fn6 = null;
    static final ModuleMethod lambda$Fn7 = null;
    static final ModuleMethod lambda$Fn8 = null;
    static final ModuleMethod lambda$Fn9 = null;
    public static trocar_senha trocar_senha;
    public Boolean $Stdebug$Mnform$St;
    public final ModuleMethod $define;
    public Label Legenda1;
    public Notifier Notificador1;

    /* renamed from: OrganizaçãoHorizontal10  reason: contains not printable characters */
    public HorizontalArrangement f440OrganizaoHorizontal10;

    /* renamed from: OrganizaçãoHorizontal2  reason: contains not printable characters */
    public HorizontalArrangement f441OrganizaoHorizontal2;

    /* renamed from: OrganizaçãoHorizontal4  reason: contains not printable characters */
    public HorizontalArrangement f442OrganizaoHorizontal4;

    /* renamed from: OrganizaçãoHorizontal5  reason: contains not printable characters */
    public HorizontalArrangement f443OrganizaoHorizontal5;

    /* renamed from: OrganizaçãoHorizontal6  reason: contains not printable characters */
    public HorizontalArrangement f444OrganizaoHorizontal6;

    /* renamed from: OrganizaçãoHorizontal7  reason: contains not printable characters */
    public HorizontalArrangement f445OrganizaoHorizontal7;

    /* renamed from: OrganizaçãoHorizontal8  reason: contains not printable characters */
    public HorizontalArrangement f446OrganizaoHorizontal8;

    /* renamed from: OrganizaçãoHorizontal9  reason: contains not printable characters */
    public HorizontalArrangement f447OrganizaoHorizontal9;

    /* renamed from: OrganizaçãoVertical1  reason: contains not printable characters */
    public VerticalArrangement f448OrganizaoVertical1;
    public final ModuleMethod add$Mnto$Mncomponents;
    public final ModuleMethod add$Mnto$Mnevents;
    public final ModuleMethod add$Mnto$Mnform$Mndo$Mnafter$Mncreation;
    public final ModuleMethod add$Mnto$Mnform$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvar$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvars;
    public final ModuleMethod android$Mnlog$Mnform;
    public Button btn_enviar;
    public final ModuleMethod btn_enviar$Click;
    public Button btn_voltarTela;
    public final ModuleMethod btn_voltarTela$Click;
    public LList components$Mnto$Mncreate;
    public PasswordTextBox cx_codigo;
    public PasswordTextBox cx_confirmarSenha;
    public TextBox cx_email;
    public PasswordTextBox cx_novaSenha;
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
    public final ModuleMethod send$Mnerror;
    public Label txt_codigo;
    public Label txt_confirmarSenha;
    public Label txt_email;
    public Label txt_nova_senha;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_TEXT).readResolve();
        Lit6 = simpleSymbol;
        Lit136 = PairWithPosition.make(simpleSymbol, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/trocar_senha.yail", 1062810);
        int[] iArr = new int[2];
        iArr[0] = -1;
        Lit121 = IntNum.make(iArr);
        int[] iArr2 = new int[2];
        iArr2[0] = -10243728;
        Lit120 = IntNum.make(iArr2);
        int[] iArr3 = new int[2];
        iArr3[0] = -14336;
        Lit105 = IntNum.make(iArr3);
        int[] iArr4 = new int[2];
        iArr4[0] = -14336;
        Lit90 = IntNum.make(iArr4);
        int[] iArr5 = new int[2];
        iArr5[0] = -14336;
        Lit74 = IntNum.make(iArr5);
        int[] iArr6 = new int[2];
        iArr6[0] = -1;
        Lit58 = IntNum.make(iArr6);
        int[] iArr7 = new int[2];
        iArr7[0] = -12303292;
        Lit8 = IntNum.make(iArr7);
    }

    public trocar_senha() {
        ModuleInfo.register(this);
        frame frame2 = new frame();
        frame2.$main = this;
        this.get$Mnsimple$Mnname = new ModuleMethod(frame2, 1, Lit140, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.onCreate = new ModuleMethod(frame2, 2, "onCreate", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.android$Mnlog$Mnform = new ModuleMethod(frame2, 3, Lit141, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnform$Mnenvironment = new ModuleMethod(frame2, 4, Lit142, 8194);
        this.lookup$Mnin$Mnform$Mnenvironment = new ModuleMethod(frame2, 5, Lit143, 8193);
        this.is$Mnbound$Mnin$Mnform$Mnenvironment = new ModuleMethod(frame2, 7, Lit144, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = new ModuleMethod(frame2, 8, Lit145, 8194);
        this.add$Mnto$Mnevents = new ModuleMethod(frame2, 9, Lit146, 8194);
        this.add$Mnto$Mncomponents = new ModuleMethod(frame2, 10, Lit147, 16388);
        this.add$Mnto$Mnglobal$Mnvars = new ModuleMethod(frame2, 11, Lit148, 8194);
        this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = new ModuleMethod(frame2, 12, Lit149, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.send$Mnerror = new ModuleMethod(frame2, 13, Lit150, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.process$Mnexception = new ModuleMethod(frame2, 14, "process-exception", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.dispatchEvent = new ModuleMethod(frame2, 15, Lit151, 16388);
        this.dispatchGenericEvent = new ModuleMethod(frame2, 16, Lit152, 16388);
        this.lookup$Mnhandler = new ModuleMethod(frame2, 17, Lit153, 8194);
        ModuleMethod moduleMethod = new ModuleMethod(frame2, 18, (Object) null, 0);
        moduleMethod.setProperty("source-location", "/tmp/runtime6229131719407907284.scm:627");
        lambda$Fn1 = moduleMethod;
        this.$define = new ModuleMethod(frame2, 19, "$define", 0);
        lambda$Fn2 = new ModuleMethod(frame2, 20, (Object) null, 0);
        lambda$Fn3 = new ModuleMethod(frame2, 21, (Object) null, 0);
        lambda$Fn4 = new ModuleMethod(frame2, 22, (Object) null, 0);
        lambda$Fn5 = new ModuleMethod(frame2, 23, (Object) null, 0);
        lambda$Fn6 = new ModuleMethod(frame2, 24, (Object) null, 0);
        this.btn_voltarTela$Click = new ModuleMethod(frame2, 25, Lit36, 0);
        lambda$Fn7 = new ModuleMethod(frame2, 26, (Object) null, 0);
        lambda$Fn8 = new ModuleMethod(frame2, 27, (Object) null, 0);
        lambda$Fn9 = new ModuleMethod(frame2, 28, (Object) null, 0);
        lambda$Fn10 = new ModuleMethod(frame2, 29, (Object) null, 0);
        lambda$Fn11 = new ModuleMethod(frame2, 30, (Object) null, 0);
        lambda$Fn12 = new ModuleMethod(frame2, 31, (Object) null, 0);
        lambda$Fn13 = new ModuleMethod(frame2, 32, (Object) null, 0);
        lambda$Fn14 = new ModuleMethod(frame2, 33, (Object) null, 0);
        lambda$Fn15 = new ModuleMethod(frame2, 34, (Object) null, 0);
        lambda$Fn16 = new ModuleMethod(frame2, 35, (Object) null, 0);
        lambda$Fn17 = new ModuleMethod(frame2, 36, (Object) null, 0);
        lambda$Fn18 = new ModuleMethod(frame2, 37, (Object) null, 0);
        lambda$Fn19 = new ModuleMethod(frame2, 38, (Object) null, 0);
        lambda$Fn20 = new ModuleMethod(frame2, 39, (Object) null, 0);
        lambda$Fn21 = new ModuleMethod(frame2, 40, (Object) null, 0);
        lambda$Fn22 = new ModuleMethod(frame2, 41, (Object) null, 0);
        lambda$Fn23 = new ModuleMethod(frame2, 42, (Object) null, 0);
        lambda$Fn24 = new ModuleMethod(frame2, 43, (Object) null, 0);
        lambda$Fn25 = new ModuleMethod(frame2, 44, (Object) null, 0);
        lambda$Fn26 = new ModuleMethod(frame2, 45, (Object) null, 0);
        lambda$Fn27 = new ModuleMethod(frame2, 46, (Object) null, 0);
        lambda$Fn28 = new ModuleMethod(frame2, 47, (Object) null, 0);
        lambda$Fn29 = new ModuleMethod(frame2, 48, (Object) null, 0);
        lambda$Fn30 = new ModuleMethod(frame2, 49, (Object) null, 0);
        lambda$Fn31 = new ModuleMethod(frame2, 50, (Object) null, 0);
        lambda$Fn32 = new ModuleMethod(frame2, 51, (Object) null, 0);
        lambda$Fn33 = new ModuleMethod(frame2, 52, (Object) null, 0);
        lambda$Fn34 = new ModuleMethod(frame2, 53, (Object) null, 0);
        lambda$Fn35 = new ModuleMethod(frame2, 54, (Object) null, 0);
        lambda$Fn36 = new ModuleMethod(frame2, 55, (Object) null, 0);
        lambda$Fn37 = new ModuleMethod(frame2, 56, (Object) null, 0);
        lambda$Fn38 = new ModuleMethod(frame2, 57, (Object) null, 0);
        lambda$Fn39 = new ModuleMethod(frame2, 58, (Object) null, 0);
        lambda$Fn40 = new ModuleMethod(frame2, 59, (Object) null, 0);
        lambda$Fn41 = new ModuleMethod(frame2, 60, (Object) null, 0);
        lambda$Fn42 = new ModuleMethod(frame2, 61, (Object) null, 0);
        lambda$Fn43 = new ModuleMethod(frame2, 62, (Object) null, 0);
        lambda$Fn44 = new ModuleMethod(frame2, 63, (Object) null, 0);
        this.btn_enviar$Click = new ModuleMethod(frame2, 64, Lit137, 0);
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
        C0718runtime.$instance.run();
        this.$Stdebug$Mnform$St = Boolean.FALSE;
        this.form$Mnenvironment = Environment.make(misc.symbol$To$String(Lit0));
        FString stringAppend = strings.stringAppend(misc.symbol$To$String(Lit0), "-global-vars");
        if (stringAppend == null) {
            obj = null;
        } else {
            obj = stringAppend.toString();
        }
        this.global$Mnvar$Mnenvironment = Environment.make(obj);
        trocar_senha = null;
        this.form$Mnname$Mnsymbol = Lit0;
        this.events$Mnto$Mnregister = LList.Empty;
        this.components$Mnto$Mncreate = LList.Empty;
        this.global$Mnvars$Mnto$Mncreate = LList.Empty;
        this.form$Mndo$Mnafter$Mncreation = LList.Empty;
        C0718runtime.$instance.run();
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit3, Boolean.TRUE, Lit4);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit5, "EVA_imobilizer", Lit6);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit7, Lit8, Lit9);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit10, "slidevertical", Lit6);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit11, "slidevertical", Lit6);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit12, "user", Lit6);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit13, Boolean.TRUE, Lit4);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit14, Boolean.TRUE, Lit4);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit15, "Responsive", Lit6);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit16, "AppTheme", Lit6);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit17, "trocar_senha", Lit6);
            Values.writeValues(C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit18, Boolean.FALSE, Lit4), $result);
        } else {
            addToFormDoAfterCreation(new Promise(lambda$Fn2));
        }
        this.Legenda1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit19, Lit20, lambda$Fn3), $result);
        } else {
            addToComponents(Lit0, Lit25, Lit20, lambda$Fn4);
        }
        this.btn_voltarTela = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit26, Lit27, lambda$Fn5), $result);
        } else {
            addToComponents(Lit0, Lit34, Lit27, lambda$Fn6);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit36, this.btn_voltarTela$Click);
        } else {
            addToFormEnvironment(Lit36, this.btn_voltarTela$Click);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "btn_voltarTela", "Click");
        } else {
            addToEvents(Lit27, Lit37);
        }
        this.f448OrganizaoVertical1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit38, Lit39, lambda$Fn7), $result);
        } else {
            addToComponents(Lit0, Lit44, Lit39, lambda$Fn8);
        }
        this.f441OrganizaoHorizontal2 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit39, Lit45, Lit46, lambda$Fn9), $result);
        } else {
            addToComponents(Lit39, Lit51, Lit46, lambda$Fn10);
        }
        this.txt_codigo = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit46, Lit52, Lit53, lambda$Fn11), $result);
        } else {
            addToComponents(Lit46, Lit59, Lit53, lambda$Fn12);
        }
        this.cx_codigo = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit46, Lit60, Lit61, lambda$Fn13), $result);
        } else {
            addToComponents(Lit46, Lit67, Lit61, lambda$Fn14);
        }
        this.f443OrganizaoHorizontal5 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit39, Lit68, Lit69, Boolean.FALSE), $result);
        } else {
            addToComponents(Lit39, Lit70, Lit69, Boolean.FALSE);
        }
        this.txt_email = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit39, Lit71, Lit72, lambda$Fn15), $result);
        } else {
            addToComponents(Lit39, Lit75, Lit72, lambda$Fn16);
        }
        this.f446OrganizaoHorizontal8 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit39, Lit76, Lit77, lambda$Fn17), $result);
        } else {
            addToComponents(Lit39, Lit79, Lit77, lambda$Fn18);
        }
        this.cx_email = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit39, Lit80, Lit81, lambda$Fn19), $result);
        } else {
            addToComponents(Lit39, Lit83, Lit81, lambda$Fn20);
        }
        this.f444OrganizaoHorizontal6 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit39, Lit84, Lit85, lambda$Fn21), $result);
        } else {
            addToComponents(Lit39, Lit86, Lit85, lambda$Fn22);
        }
        this.txt_nova_senha = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit39, Lit87, Lit88, lambda$Fn23), $result);
        } else {
            addToComponents(Lit39, Lit91, Lit88, lambda$Fn24);
        }
        this.f447OrganizaoHorizontal9 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit39, Lit92, Lit93, lambda$Fn25), $result);
        } else {
            addToComponents(Lit39, Lit94, Lit93, lambda$Fn26);
        }
        this.cx_novaSenha = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit39, Lit95, Lit96, lambda$Fn27), $result);
        } else {
            addToComponents(Lit39, Lit98, Lit96, lambda$Fn28);
        }
        this.f445OrganizaoHorizontal7 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit39, Lit99, Lit100, lambda$Fn29), $result);
        } else {
            addToComponents(Lit39, Lit101, Lit100, lambda$Fn30);
        }
        this.txt_confirmarSenha = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit39, Lit102, Lit103, lambda$Fn31), $result);
        } else {
            addToComponents(Lit39, Lit106, Lit103, lambda$Fn32);
        }
        this.f440OrganizaoHorizontal10 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit39, Lit107, Lit108, lambda$Fn33), $result);
        } else {
            addToComponents(Lit39, Lit109, Lit108, lambda$Fn34);
        }
        this.cx_confirmarSenha = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit39, Lit110, Lit111, lambda$Fn35), $result);
        } else {
            addToComponents(Lit39, Lit113, Lit111, lambda$Fn36);
        }
        this.f442OrganizaoHorizontal4 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit39, Lit114, Lit115, lambda$Fn37), $result);
        } else {
            addToComponents(Lit39, Lit117, Lit115, lambda$Fn38);
        }
        this.btn_enviar = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit39, Lit118, Lit119, lambda$Fn39), $result);
        } else {
            addToComponents(Lit39, Lit122, Lit119, lambda$Fn40);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit137, this.btn_enviar$Click);
        } else {
            addToFormEnvironment(Lit137, this.btn_enviar$Click);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "btn_enviar", "Click");
        } else {
            addToEvents(Lit119, Lit37);
        }
        this.Notificador1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit138, Lit131, Boolean.FALSE), $result);
        } else {
            addToComponents(Lit0, Lit139, Lit131, Boolean.FALSE);
        }
        C0718runtime.initRuntime();
    }

    static Object lambda3() {
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit3, Boolean.TRUE, Lit4);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit5, "EVA_imobilizer", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit7, Lit8, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit10, "slidevertical", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit11, "slidevertical", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit12, "user", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit13, Boolean.TRUE, Lit4);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit14, Boolean.TRUE, Lit4);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit15, "Responsive", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit16, "AppTheme", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit17, "trocar_senha", Lit6);
        return C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit18, Boolean.FALSE, Lit4);
    }

    static Object lambda4() {
        C0718runtime.setAndCoerceProperty$Ex(Lit20, Lit21, Lit22, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit20, Lit23, Lit24, Lit9);
    }

    static Object lambda5() {
        C0718runtime.setAndCoerceProperty$Ex(Lit20, Lit21, Lit22, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit20, Lit23, Lit24, Lit9);
    }

    static Object lambda6() {
        C0718runtime.setAndCoerceProperty$Ex(Lit27, Lit7, Lit28, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit27, Lit21, Lit29, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit27, Lit30, "setaCorreta-removebg-preview.png", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit27, Lit23, Lit31, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit27, Lit32, Lit33, Lit9);
    }

    static Object lambda7() {
        C0718runtime.setAndCoerceProperty$Ex(Lit27, Lit7, Lit28, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit27, Lit21, Lit29, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit27, Lit30, "setaCorreta-removebg-preview.png", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit27, Lit23, Lit31, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit27, Lit32, Lit33, Lit9);
    }

    public Object btn_voltarTela$Click() {
        C0718runtime.setThisForm();
        return C0718runtime.callYailPrimitive(C0718runtime.open$Mnanother$Mnscreen, LList.list1("recuperar_senha"), Lit35, "open another screen");
    }

    static Object lambda8() {
        C0718runtime.setAndCoerceProperty$Ex(Lit39, Lit40, Lit41, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit39, Lit7, Lit42, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit39, Lit21, Lit43, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit39, Lit32, Lit43, Lit9);
    }

    static Object lambda9() {
        C0718runtime.setAndCoerceProperty$Ex(Lit39, Lit40, Lit41, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit39, Lit7, Lit42, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit39, Lit21, Lit43, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit39, Lit32, Lit43, Lit9);
    }

    static Object lambda10() {
        C0718runtime.setAndCoerceProperty$Ex(Lit46, Lit40, Lit41, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit46, Lit47, Lit48, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit46, Lit7, Lit49, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit46, Lit21, Lit50, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit46, Lit32, Lit43, Lit9);
    }

    static Object lambda11() {
        C0718runtime.setAndCoerceProperty$Ex(Lit46, Lit40, Lit41, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit46, Lit47, Lit48, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit46, Lit7, Lit49, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit46, Lit21, Lit50, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit46, Lit32, Lit43, Lit9);
    }

    static Object lambda12() {
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit54, Lit55, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit21, Lit56, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit57, "Informe o código", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit23, Lit58, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit32, Lit43, Lit9);
    }

    static Object lambda13() {
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit54, Lit55, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit21, Lit56, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit57, "Informe o código", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit23, Lit58, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit32, Lit43, Lit9);
    }

    static Object lambda14() {
        C0718runtime.setAndCoerceProperty$Ex(Lit61, Lit54, Lit62, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit61, Lit21, Lit63, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit61, Lit64, "*******", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit61, Lit65, Lit66, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit61, Lit32, Lit43, Lit9);
    }

    static Object lambda15() {
        C0718runtime.setAndCoerceProperty$Ex(Lit61, Lit54, Lit62, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit61, Lit21, Lit63, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit61, Lit64, "*******", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit61, Lit65, Lit66, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit61, Lit32, Lit43, Lit9);
    }

    static Object lambda16() {
        C0718runtime.setAndCoerceProperty$Ex(Lit72, Lit54, Lit55, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit72, Lit21, Lit73, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit72, Lit57, "Informe o email", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit72, Lit65, Lit66, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit72, Lit23, Lit74, Lit9);
    }

    static Object lambda17() {
        C0718runtime.setAndCoerceProperty$Ex(Lit72, Lit54, Lit55, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit72, Lit21, Lit73, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit72, Lit57, "Informe o email", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit72, Lit65, Lit66, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit72, Lit23, Lit74, Lit9);
    }

    static Object lambda18() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit77, Lit21, Lit78, Lit9);
    }

    static Object lambda19() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit77, Lit21, Lit78, Lit9);
    }

    static Object lambda20() {
        C0718runtime.setAndCoerceProperty$Ex(Lit81, Lit54, Lit62, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit81, Lit21, Lit82, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit81, Lit64, "usuario@gmail.com", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit81, Lit65, Lit66, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit81, Lit32, Lit43, Lit9);
    }

    static Object lambda21() {
        C0718runtime.setAndCoerceProperty$Ex(Lit81, Lit54, Lit62, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit81, Lit21, Lit82, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit81, Lit64, "usuario@gmail.com", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit81, Lit65, Lit66, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit81, Lit32, Lit43, Lit9);
    }

    static Object lambda22() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit85, Lit21, Lit62, Lit9);
    }

    static Object lambda23() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit85, Lit21, Lit62, Lit9);
    }

    static Object lambda24() {
        C0718runtime.setAndCoerceProperty$Ex(Lit88, Lit54, Lit55, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit88, Lit21, Lit89, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit88, Lit57, "Digite uma nova senha", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit88, Lit65, Lit66, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit88, Lit23, Lit90, Lit9);
    }

    static Object lambda25() {
        C0718runtime.setAndCoerceProperty$Ex(Lit88, Lit54, Lit55, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit88, Lit21, Lit89, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit88, Lit57, "Digite uma nova senha", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit88, Lit65, Lit66, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit88, Lit23, Lit90, Lit9);
    }

    static Object lambda26() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit93, Lit21, Lit78, Lit9);
    }

    static Object lambda27() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit93, Lit21, Lit78, Lit9);
    }

    static Object lambda28() {
        C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit54, Lit62, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit21, Lit97, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit64, "******", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit65, Lit66, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit32, Lit43, Lit9);
    }

    static Object lambda29() {
        C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit54, Lit62, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit21, Lit97, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit64, "******", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit65, Lit66, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit32, Lit43, Lit9);
    }

    static Object lambda30() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit100, Lit21, Lit62, Lit9);
    }

    static Object lambda31() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit100, Lit21, Lit62, Lit9);
    }

    static Object lambda32() {
        C0718runtime.setAndCoerceProperty$Ex(Lit103, Lit54, Lit55, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit103, Lit21, Lit104, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit103, Lit57, "Confirme sua nova senha", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit103, Lit65, Lit66, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit103, Lit23, Lit105, Lit9);
    }

    static Object lambda33() {
        C0718runtime.setAndCoerceProperty$Ex(Lit103, Lit54, Lit55, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit103, Lit21, Lit104, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit103, Lit57, "Confirme sua nova senha", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit103, Lit65, Lit66, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit103, Lit23, Lit105, Lit9);
    }

    static Object lambda34() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit108, Lit21, Lit78, Lit9);
    }

    static Object lambda35() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit108, Lit21, Lit78, Lit9);
    }

    static Object lambda36() {
        C0718runtime.setAndCoerceProperty$Ex(Lit111, Lit54, Lit62, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit111, Lit21, Lit112, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit111, Lit64, "******", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit111, Lit65, Lit66, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit111, Lit32, Lit43, Lit9);
    }

    static Object lambda37() {
        C0718runtime.setAndCoerceProperty$Ex(Lit111, Lit54, Lit62, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit111, Lit21, Lit112, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit111, Lit64, "******", Lit6);
        C0718runtime.setAndCoerceProperty$Ex(Lit111, Lit65, Lit66, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit111, Lit32, Lit43, Lit9);
    }

    static Object lambda38() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit115, Lit21, Lit116, Lit9);
    }

    static Object lambda39() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit115, Lit21, Lit116, Lit9);
    }

    static Object lambda40() {
        C0718runtime.setAndCoerceProperty$Ex(Lit119, Lit7, Lit120, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit119, Lit54, Lit55, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit119, Lit57, "Enviar", Lit6);
        return C0718runtime.setAndCoerceProperty$Ex(Lit119, Lit23, Lit121, Lit9);
    }

    static Object lambda41() {
        C0718runtime.setAndCoerceProperty$Ex(Lit119, Lit7, Lit120, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit119, Lit54, Lit55, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit119, Lit57, "Enviar", Lit6);
        return C0718runtime.setAndCoerceProperty$Ex(Lit119, Lit23, Lit121, Lit9);
    }

    public Object btn_enviar$Click() {
        C0718runtime.setThisForm();
        if (C0718runtime.callYailPrimitive(strings.string$Eq$Qu, LList.list2(C0718runtime.get$Mnproperty.apply2(Lit61, Lit57), "123"), Lit123, "text=") == Boolean.FALSE) {
            C0718runtime.callComponentMethod(Lit131, Lit132, LList.list1("Código incorreto"), Lit136);
            return C0718runtime.setAndCoerceProperty$Ex(Lit61, Lit57, "", Lit6);
        } else if (C0718runtime.callYailPrimitive(strings.string$Eq$Qu, LList.list2(C0718runtime.get$Mnproperty.apply2(Lit81, Lit57), "usuario@gmail.com"), Lit124, "text=") != Boolean.FALSE) {
            if (C0718runtime.processAndDelayed$V(new Object[]{lambda$Fn41, lambda$Fn42, lambda$Fn43, lambda$Fn44}) == Boolean.FALSE) {
                C0718runtime.callComponentMethod(Lit131, Lit132, LList.list1("Senha inválida"), Lit134);
                C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit57, "", Lit6);
                return C0718runtime.setAndCoerceProperty$Ex(Lit111, Lit57, "", Lit6);
            } else if (C0718runtime.callYailPrimitive(strings.string$Eq$Qu, LList.list2(C0718runtime.get$Mnproperty.apply2(Lit96, Lit57), C0718runtime.get$Mnproperty.apply2(Lit111, Lit57)), Lit129, "text=") != Boolean.FALSE) {
                C0718runtime.setAndCoerceProperty$Ex(Lit61, Lit57, "", Lit6);
                C0718runtime.setAndCoerceProperty$Ex(Lit81, Lit57, "", Lit6);
                C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit57, "", Lit6);
                C0718runtime.setAndCoerceProperty$Ex(Lit111, Lit57, "", Lit6);
                return C0718runtime.callYailPrimitive(C0718runtime.open$Mnanother$Mnscreen, LList.list1("Screen1"), Lit130, "open another screen");
            } else {
                C0718runtime.callComponentMethod(Lit131, Lit132, LList.Empty, Lit133);
                C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit57, "", Lit6);
                return C0718runtime.setAndCoerceProperty$Ex(Lit111, Lit57, "", Lit6);
            }
        } else {
            C0718runtime.callComponentMethod(Lit131, Lit132, LList.list1("Login incorreto"), Lit135);
            return C0718runtime.setAndCoerceProperty$Ex(Lit81, Lit57, "", Lit6);
        }
    }

    static boolean lambda42() {
        return ((C0718runtime.callYailPrimitive(strings.string$Eq$Qu, LList.list2(C0718runtime.get$Mnproperty.apply2(Lit96, Lit57), "123"), Lit125, "not =") != Boolean.FALSE ? 1 : 0) + 1) & true;
    }

    static Object lambda43() {
        return C0718runtime.callYailPrimitive(strings.string$Ls$Qu, LList.list2(C0718runtime.get$Mnproperty.apply2(Lit96, Lit57), "99999"), Lit126, "text<");
    }

    static boolean lambda44() {
        return ((C0718runtime.callYailPrimitive(strings.string$Eq$Qu, LList.list2(C0718runtime.get$Mnproperty.apply2(Lit96, Lit57), "99999"), Lit127, "not =") != Boolean.FALSE ? 1 : 0) + 1) & true;
    }

    static boolean lambda45() {
        return ((C0718runtime.callYailPrimitive(strings.string$Eq$Qu, LList.list2(C0718runtime.get$Mnproperty.apply2(Lit96, Lit57), ""), Lit128, "not =") != Boolean.FALSE ? 1 : 0) + 1) & true;
    }

    /* compiled from: trocar_senha.yail */
    public class frame extends ModuleBody {
        trocar_senha $main;

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 1:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 2:
                    if (!(obj instanceof trocar_senha)) {
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
                    if (!(obj instanceof trocar_senha)) {
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
                    if (!(obj instanceof trocar_senha)) {
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
                    if (!(obj instanceof trocar_senha)) {
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
                    trocar_senha trocar_senha = this.$main;
                    try {
                        Component component = (Component) obj;
                        try {
                            String str = (String) obj2;
                            try {
                                if (obj3 == Boolean.FALSE) {
                                    z = false;
                                }
                                try {
                                    trocar_senha.dispatchGenericEvent(component, str, z, (Object[]) obj4);
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
                    return trocar_senha.lambda2();
                case 19:
                    this.$main.$define();
                    return Values.empty;
                case 20:
                    return trocar_senha.lambda3();
                case 21:
                    return trocar_senha.lambda4();
                case 22:
                    return trocar_senha.lambda5();
                case 23:
                    return trocar_senha.lambda6();
                case 24:
                    return trocar_senha.lambda7();
                case 25:
                    return this.$main.btn_voltarTela$Click();
                case 26:
                    return trocar_senha.lambda8();
                case 27:
                    return trocar_senha.lambda9();
                case 28:
                    return trocar_senha.lambda10();
                case 29:
                    return trocar_senha.lambda11();
                case 30:
                    return trocar_senha.lambda12();
                case 31:
                    return trocar_senha.lambda13();
                case 32:
                    return trocar_senha.lambda14();
                case 33:
                    return trocar_senha.lambda15();
                case 34:
                    return trocar_senha.lambda16();
                case 35:
                    return trocar_senha.lambda17();
                case 36:
                    return trocar_senha.lambda18();
                case 37:
                    return trocar_senha.lambda19();
                case 38:
                    return trocar_senha.lambda20();
                case 39:
                    return trocar_senha.lambda21();
                case 40:
                    return trocar_senha.lambda22();
                case 41:
                    return trocar_senha.lambda23();
                case 42:
                    return trocar_senha.lambda24();
                case 43:
                    return trocar_senha.lambda25();
                case 44:
                    return trocar_senha.lambda26();
                case 45:
                    return trocar_senha.lambda27();
                case 46:
                    return trocar_senha.lambda28();
                case 47:
                    return trocar_senha.lambda29();
                case 48:
                    return trocar_senha.lambda30();
                case 49:
                    return trocar_senha.lambda31();
                case 50:
                    return trocar_senha.lambda32();
                case 51:
                    return trocar_senha.lambda33();
                case 52:
                    return trocar_senha.lambda34();
                case 53:
                    return trocar_senha.lambda35();
                case 54:
                    return trocar_senha.lambda36();
                case 55:
                    return trocar_senha.lambda37();
                case 56:
                    return trocar_senha.lambda38();
                case 57:
                    return trocar_senha.lambda39();
                case 58:
                    return trocar_senha.lambda40();
                case 59:
                    return trocar_senha.lambda41();
                case 60:
                    return trocar_senha.lambda42() ? Boolean.TRUE : Boolean.FALSE;
                case 61:
                    return trocar_senha.lambda43();
                case 62:
                    return trocar_senha.lambda44() ? Boolean.TRUE : Boolean.FALSE;
                case 63:
                    return trocar_senha.lambda45() ? Boolean.TRUE : Boolean.FALSE;
                case 64:
                    return this.$main.btn_enviar$Click();
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
                case 46:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 47:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 48:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 49:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 50:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 51:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 52:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 53:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 54:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 55:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 56:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 57:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 58:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 59:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 60:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 61:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 62:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 63:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 64:
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
        trocar_senha = this;
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
