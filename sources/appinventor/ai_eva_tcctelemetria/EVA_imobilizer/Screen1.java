package appinventor.ai_eva_tcctelemetria.EVA_imobilizer;

import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.AppInventorCompatActivity;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.FirebaseDB;
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

/* compiled from: Screen1.yail */
public class Screen1 extends Form implements Runnable {
    static final SimpleSymbol Lit0 = ((SimpleSymbol) new SimpleSymbol("Screen1").readResolve());
    static final SimpleSymbol Lit1 = ((SimpleSymbol) new SimpleSymbol("getMessage").readResolve());
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit100 = ((SimpleSymbol) new SimpleSymbol("btn_esqueciSenha").readResolve());
    static final IntNum Lit101;
    static final SimpleSymbol Lit102 = ((SimpleSymbol) new SimpleSymbol("FontItalic").readResolve());
    static final IntNum Lit103 = IntNum.make(10);
    static final IntNum Lit104;
    static final FString Lit105 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit106 = PairWithPosition.make(Lit10, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 729174);
    static final SimpleSymbol Lit107 = ((SimpleSymbol) new SimpleSymbol("btn_esqueciSenha$Click").readResolve());
    static final FString Lit108 = new FString("com.google.appinventor.components.runtime.Notifier");
    static final SimpleSymbol Lit109 = ((SimpleSymbol) new SimpleSymbol("Notificador1").readResolve());
    static final SimpleSymbol Lit11 = ((SimpleSymbol) new SimpleSymbol("BackgroundColor").readResolve());
    static final FString Lit110 = new FString("com.google.appinventor.components.runtime.Notifier");
    static final FString Lit111 = new FString("com.google.appinventor.components.runtime.FirebaseDB");
    static final SimpleSymbol Lit112 = ((SimpleSymbol) new SimpleSymbol("DefaultURL").readResolve());
    static final SimpleSymbol Lit113 = ((SimpleSymbol) new SimpleSymbol("DeveloperBucket").readResolve());
    static final SimpleSymbol Lit114 = ((SimpleSymbol) new SimpleSymbol("FirebaseToken").readResolve());
    static final SimpleSymbol Lit115 = ((SimpleSymbol) new SimpleSymbol("FirebaseURL").readResolve());
    static final SimpleSymbol Lit116 = ((SimpleSymbol) new SimpleSymbol("ProjectBucket").readResolve());
    static final FString Lit117 = new FString("com.google.appinventor.components.runtime.FirebaseDB");
    static final SimpleSymbol Lit118 = ((SimpleSymbol) new SimpleSymbol("$value").readResolve());
    static final PairWithPosition Lit119 = PairWithPosition.make(Lit144, PairWithPosition.make(Lit8, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 823464), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 823458);
    static final IntNum Lit12;
    static final PairWithPosition Lit120 = PairWithPosition.make(Lit10, PairWithPosition.make(Lit10, PairWithPosition.make(Lit10, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 823604), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 823599), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 823593);
    static final PairWithPosition Lit121 = PairWithPosition.make(Lit143, PairWithPosition.make(Lit143, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 823632), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 823627);
    static final PairWithPosition Lit122;
    static final PairWithPosition Lit123 = PairWithPosition.make(Lit143, PairWithPosition.make(Lit143, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 823854), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 823849);
    static final PairWithPosition Lit124 = PairWithPosition.make(Lit10, PairWithPosition.make(Lit143, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 823992), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 823986);
    static final SimpleSymbol Lit125 = ((SimpleSymbol) new SimpleSymbol("ShowAlert").readResolve());
    static final PairWithPosition Lit126;
    static final SimpleSymbol Lit127 = ((SimpleSymbol) new SimpleSymbol("FirebaseDB1$GotValue").readResolve());
    static final SimpleSymbol Lit128 = ((SimpleSymbol) new SimpleSymbol("GotValue").readResolve());
    static final SimpleSymbol Lit129 = ((SimpleSymbol) new SimpleSymbol("get-simple-name").readResolve());
    static final SimpleSymbol Lit13 = ((SimpleSymbol) new SimpleSymbol("CloseScreenAnimation").readResolve());
    static final SimpleSymbol Lit130 = ((SimpleSymbol) new SimpleSymbol("android-log-form").readResolve());
    static final SimpleSymbol Lit131 = ((SimpleSymbol) new SimpleSymbol("add-to-form-environment").readResolve());
    static final SimpleSymbol Lit132 = ((SimpleSymbol) new SimpleSymbol("lookup-in-form-environment").readResolve());
    static final SimpleSymbol Lit133 = ((SimpleSymbol) new SimpleSymbol("is-bound-in-form-environment").readResolve());
    static final SimpleSymbol Lit134 = ((SimpleSymbol) new SimpleSymbol("add-to-global-var-environment").readResolve());
    static final SimpleSymbol Lit135 = ((SimpleSymbol) new SimpleSymbol("add-to-events").readResolve());
    static final SimpleSymbol Lit136 = ((SimpleSymbol) new SimpleSymbol("add-to-components").readResolve());
    static final SimpleSymbol Lit137 = ((SimpleSymbol) new SimpleSymbol("add-to-global-vars").readResolve());
    static final SimpleSymbol Lit138 = ((SimpleSymbol) new SimpleSymbol("add-to-form-do-after-creation").readResolve());
    static final SimpleSymbol Lit139 = ((SimpleSymbol) new SimpleSymbol("send-error").readResolve());
    static final SimpleSymbol Lit14 = ((SimpleSymbol) new SimpleSymbol("Icon").readResolve());
    static final SimpleSymbol Lit140 = ((SimpleSymbol) new SimpleSymbol("dispatchEvent").readResolve());
    static final SimpleSymbol Lit141 = ((SimpleSymbol) new SimpleSymbol("dispatchGenericEvent").readResolve());
    static final SimpleSymbol Lit142 = ((SimpleSymbol) new SimpleSymbol("lookup-handler").readResolve());
    static final SimpleSymbol Lit143 = ((SimpleSymbol) new SimpleSymbol("any").readResolve());
    static final SimpleSymbol Lit144 = ((SimpleSymbol) new SimpleSymbol("list").readResolve());
    static final SimpleSymbol Lit15 = ((SimpleSymbol) new SimpleSymbol("OpenScreenAnimation").readResolve());
    static final SimpleSymbol Lit16 = ((SimpleSymbol) new SimpleSymbol("ScreenOrientation").readResolve());
    static final SimpleSymbol Lit17 = ((SimpleSymbol) new SimpleSymbol("Scrollable").readResolve());
    static final SimpleSymbol Lit18 = ((SimpleSymbol) new SimpleSymbol("ShowListsAsJson").readResolve());
    static final SimpleSymbol Lit19 = ((SimpleSymbol) new SimpleSymbol("Sizing").readResolve());
    static final SimpleSymbol Lit2 = ((SimpleSymbol) new SimpleSymbol("*the-null-value*").readResolve());
    static final SimpleSymbol Lit20 = ((SimpleSymbol) new SimpleSymbol("Theme").readResolve());
    static final SimpleSymbol Lit21 = ((SimpleSymbol) new SimpleSymbol("Title").readResolve());
    static final SimpleSymbol Lit22 = ((SimpleSymbol) new SimpleSymbol("TitleVisible").readResolve());
    static final FString Lit23 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    static final SimpleSymbol Lit24 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoVertical2").readResolve());
    static final SimpleSymbol Lit25 = ((SimpleSymbol) new SimpleSymbol("Height").readResolve());
    static final IntNum Lit26 = IntNum.make(20);
    static final FString Lit27 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    static final FString Lit28 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit29 = ((SimpleSymbol) new SimpleSymbol("txt_Titulo").readResolve());
    static final SimpleSymbol Lit3 = ((SimpleSymbol) new SimpleSymbol("g$acesso").readResolve());
    static final SimpleSymbol Lit30 = ((SimpleSymbol) new SimpleSymbol("FontBold").readResolve());
    static final SimpleSymbol Lit31 = ((SimpleSymbol) new SimpleSymbol("FontSize").readResolve());
    static final IntNum Lit32 = IntNum.make(30);
    static final SimpleSymbol Lit33 = ((SimpleSymbol) new SimpleSymbol("Text").readResolve());
    static final SimpleSymbol Lit34 = ((SimpleSymbol) new SimpleSymbol("TextColor").readResolve());
    static final IntNum Lit35;
    static final FString Lit36 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit37 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit38 = ((SimpleSymbol) new SimpleSymbol("HorizontalArrangement4").readResolve());
    static final FString Lit39 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit4 = ((SimpleSymbol) new SimpleSymbol("ActionBar").readResolve());
    static final FString Lit40 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit41 = ((SimpleSymbol) new SimpleSymbol("txt_login").readResolve());
    static final IntNum Lit42;
    static final FString Lit43 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit44 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit45 = ((SimpleSymbol) new SimpleSymbol("HorizontalArrangement1").readResolve());
    static final IntNum Lit46 = IntNum.make(5);
    static final FString Lit47 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit48 = new FString("com.google.appinventor.components.runtime.TextBox");
    static final SimpleSymbol Lit49 = ((SimpleSymbol) new SimpleSymbol("cx_login").readResolve());
    static final SimpleSymbol Lit5 = ((SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN).readResolve());
    static final IntNum Lit50 = IntNum.make(15);
    static final SimpleSymbol Lit51 = ((SimpleSymbol) new SimpleSymbol("Hint").readResolve());
    static final SimpleSymbol Lit52 = ((SimpleSymbol) new SimpleSymbol("TextAlignment").readResolve());
    static final IntNum Lit53 = IntNum.make(1);
    static final SimpleSymbol Lit54 = ((SimpleSymbol) new SimpleSymbol("Width").readResolve());
    static final IntNum Lit55 = IntNum.make(250);
    static final FString Lit56 = new FString("com.google.appinventor.components.runtime.TextBox");
    static final FString Lit57 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit58 = ((SimpleSymbol) new SimpleSymbol("HorizontalArrangement3").readResolve());
    static final FString Lit59 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit6 = ((SimpleSymbol) new SimpleSymbol("AlignHorizontal").readResolve());
    static final FString Lit60 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit61 = ((SimpleSymbol) new SimpleSymbol("txt_senha").readResolve());
    static final IntNum Lit62;
    static final FString Lit63 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit64 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit65 = ((SimpleSymbol) new SimpleSymbol("HorizontalArrangement2").readResolve());
    static final FString Lit66 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit67 = new FString("com.google.appinventor.components.runtime.PasswordTextBox");
    static final SimpleSymbol Lit68 = ((SimpleSymbol) new SimpleSymbol("cx_senha").readResolve());
    static final FString Lit69 = new FString("com.google.appinventor.components.runtime.PasswordTextBox");
    static final IntNum Lit7 = IntNum.make(3);
    static final FString Lit70 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    static final SimpleSymbol Lit71 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoVertical1").readResolve());
    static final FString Lit72 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    static final FString Lit73 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit74 = ((SimpleSymbol) new SimpleSymbol("btn_entrar").readResolve());
    static final IntNum Lit75;
    static final IntNum Lit76;
    static final IntNum Lit77 = IntNum.make(-1050);
    static final FString Lit78 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit79 = ((SimpleSymbol) new SimpleSymbol("FirebaseDB1").readResolve());
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit80 = ((SimpleSymbol) new SimpleSymbol("GetValue").readResolve());
    static final PairWithPosition Lit81 = PairWithPosition.make(Lit10, PairWithPosition.make(Lit10, PairWithPosition.make(Lit10, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 590007), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 590002), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 589996);
    static final IntNum Lit82 = IntNum.make(999);
    static final PairWithPosition Lit83 = PairWithPosition.make(Lit8, PairWithPosition.make(Lit8, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 590157), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 590149);
    static final PairWithPosition Lit84 = PairWithPosition.make(Lit8, PairWithPosition.make(Lit8, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 590255), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 590247);
    static final PairWithPosition Lit85 = PairWithPosition.make(Lit8, PairWithPosition.make(Lit8, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 590353), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 590345);
    static final PairWithPosition Lit86 = PairWithPosition.make(Lit8, PairWithPosition.make(Lit8, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 590451), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 590443);
    static final PairWithPosition Lit87 = PairWithPosition.make(Lit143, PairWithPosition.make(Lit143, PairWithPosition.make(Lit143, PairWithPosition.make(Lit143, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 590493), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 590489), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 590485), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 590480);
    static final PairWithPosition Lit88 = PairWithPosition.make(Lit10, PairWithPosition.make(Lit143, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 590522), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 590516);
    static final SimpleSymbol Lit89 = ((SimpleSymbol) new SimpleSymbol("btn_entrar$Click").readResolve());
    static final SimpleSymbol Lit9 = ((SimpleSymbol) new SimpleSymbol("AppName").readResolve());
    static final SimpleSymbol Lit90 = ((SimpleSymbol) new SimpleSymbol("Click").readResolve());
    static final FString Lit91 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit92 = ((SimpleSymbol) new SimpleSymbol("btn_cadastrar").readResolve());
    static final IntNum Lit93;
    static final IntNum Lit94;
    static final IntNum Lit95 = IntNum.make(-1050);
    static final FString Lit96 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit97 = PairWithPosition.make(Lit10, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 659544);
    static final SimpleSymbol Lit98 = ((SimpleSymbol) new SimpleSymbol("btn_cadastrar$Click").readResolve());
    static final FString Lit99 = new FString("com.google.appinventor.components.runtime.Button");
    public static Screen1 Screen1;
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
    static final ModuleMethod lambda$Fn4 = null;
    static final ModuleMethod lambda$Fn5 = null;
    static final ModuleMethod lambda$Fn6 = null;
    static final ModuleMethod lambda$Fn7 = null;
    static final ModuleMethod lambda$Fn8 = null;
    static final ModuleMethod lambda$Fn9 = null;
    public Boolean $Stdebug$Mnform$St;
    public final ModuleMethod $define;
    public FirebaseDB FirebaseDB1;
    public final ModuleMethod FirebaseDB1$GotValue;
    public HorizontalArrangement HorizontalArrangement1;
    public HorizontalArrangement HorizontalArrangement2;
    public HorizontalArrangement HorizontalArrangement3;
    public HorizontalArrangement HorizontalArrangement4;
    public Notifier Notificador1;

    /* renamed from: OrganizaçãoVertical1  reason: contains not printable characters */
    public VerticalArrangement f418OrganizaoVertical1;

    /* renamed from: OrganizaçãoVertical2  reason: contains not printable characters */
    public VerticalArrangement f419OrganizaoVertical2;
    public final ModuleMethod add$Mnto$Mncomponents;
    public final ModuleMethod add$Mnto$Mnevents;
    public final ModuleMethod add$Mnto$Mnform$Mndo$Mnafter$Mncreation;
    public final ModuleMethod add$Mnto$Mnform$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvar$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvars;
    public final ModuleMethod android$Mnlog$Mnform;
    public Button btn_cadastrar;
    public final ModuleMethod btn_cadastrar$Click;
    public Button btn_entrar;
    public final ModuleMethod btn_entrar$Click;
    public Button btn_esqueciSenha;
    public final ModuleMethod btn_esqueciSenha$Click;
    public LList components$Mnto$Mncreate;
    public TextBox cx_login;
    public PasswordTextBox cx_senha;
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
    public Label txt_Titulo;
    public Label txt_login;
    public Label txt_senha;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_TEXT).readResolve();
        Lit10 = simpleSymbol;
        Lit126 = PairWithPosition.make(simpleSymbol, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 824151);
        SimpleSymbol simpleSymbol2 = Lit144;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("number").readResolve();
        Lit8 = simpleSymbol3;
        Lit122 = PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol3, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 823788), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Screen1.yail", 823782);
        int[] iArr = new int[2];
        iArr[0] = -1;
        Lit104 = IntNum.make(iArr);
        int[] iArr2 = new int[2];
        iArr2[0] = -12303292;
        Lit101 = IntNum.make(iArr2);
        int[] iArr3 = new int[2];
        iArr3[0] = -1;
        Lit94 = IntNum.make(iArr3);
        int[] iArr4 = new int[2];
        iArr4[0] = -12560655;
        Lit93 = IntNum.make(iArr4);
        int[] iArr5 = new int[2];
        iArr5[0] = -1;
        Lit76 = IntNum.make(iArr5);
        int[] iArr6 = new int[2];
        iArr6[0] = -10243728;
        Lit75 = IntNum.make(iArr6);
        int[] iArr7 = new int[2];
        iArr7[0] = -14336;
        Lit62 = IntNum.make(iArr7);
        int[] iArr8 = new int[2];
        iArr8[0] = -14336;
        Lit42 = IntNum.make(iArr8);
        int[] iArr9 = new int[2];
        iArr9[0] = -14336;
        Lit35 = IntNum.make(iArr9);
        int[] iArr10 = new int[2];
        iArr10[0] = -12303292;
        Lit12 = IntNum.make(iArr10);
    }

    public Screen1() {
        ModuleInfo.register(this);
        frame frame2 = new frame();
        frame2.$main = this;
        this.get$Mnsimple$Mnname = new ModuleMethod(frame2, 3, Lit129, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.onCreate = new ModuleMethod(frame2, 4, "onCreate", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.android$Mnlog$Mnform = new ModuleMethod(frame2, 5, Lit130, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnform$Mnenvironment = new ModuleMethod(frame2, 6, Lit131, 8194);
        this.lookup$Mnin$Mnform$Mnenvironment = new ModuleMethod(frame2, 7, Lit132, 8193);
        this.is$Mnbound$Mnin$Mnform$Mnenvironment = new ModuleMethod(frame2, 9, Lit133, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = new ModuleMethod(frame2, 10, Lit134, 8194);
        this.add$Mnto$Mnevents = new ModuleMethod(frame2, 11, Lit135, 8194);
        this.add$Mnto$Mncomponents = new ModuleMethod(frame2, 12, Lit136, 16388);
        this.add$Mnto$Mnglobal$Mnvars = new ModuleMethod(frame2, 13, Lit137, 8194);
        this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = new ModuleMethod(frame2, 14, Lit138, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.send$Mnerror = new ModuleMethod(frame2, 15, Lit139, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.process$Mnexception = new ModuleMethod(frame2, 16, "process-exception", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.dispatchEvent = new ModuleMethod(frame2, 17, Lit140, 16388);
        this.dispatchGenericEvent = new ModuleMethod(frame2, 18, Lit141, 16388);
        this.lookup$Mnhandler = new ModuleMethod(frame2, 19, Lit142, 8194);
        ModuleMethod moduleMethod = new ModuleMethod(frame2, 20, (Object) null, 0);
        moduleMethod.setProperty("source-location", "/tmp/runtime6229131719407907284.scm:627");
        lambda$Fn1 = moduleMethod;
        this.$define = new ModuleMethod(frame2, 21, "$define", 0);
        lambda$Fn2 = new ModuleMethod(frame2, 22, (Object) null, 0);
        lambda$Fn3 = new ModuleMethod(frame2, 23, (Object) null, 0);
        lambda$Fn4 = new ModuleMethod(frame2, 24, (Object) null, 0);
        lambda$Fn5 = new ModuleMethod(frame2, 25, (Object) null, 0);
        lambda$Fn6 = new ModuleMethod(frame2, 26, (Object) null, 0);
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
        lambda$Fn23 = new ModuleMethod(frame2, 43, (Object) null, 0);
        lambda$Fn24 = new ModuleMethod(frame2, 44, (Object) null, 0);
        lambda$Fn25 = new ModuleMethod(frame2, 45, (Object) null, 0);
        this.btn_entrar$Click = new ModuleMethod(frame2, 46, Lit89, 0);
        lambda$Fn26 = new ModuleMethod(frame2, 47, (Object) null, 0);
        lambda$Fn27 = new ModuleMethod(frame2, 48, (Object) null, 0);
        this.btn_cadastrar$Click = new ModuleMethod(frame2, 49, Lit98, 0);
        lambda$Fn28 = new ModuleMethod(frame2, 50, (Object) null, 0);
        lambda$Fn29 = new ModuleMethod(frame2, 51, (Object) null, 0);
        this.btn_esqueciSenha$Click = new ModuleMethod(frame2, 52, Lit107, 0);
        lambda$Fn30 = new ModuleMethod(frame2, 53, (Object) null, 0);
        lambda$Fn31 = new ModuleMethod(frame2, 54, (Object) null, 0);
        this.FirebaseDB1$GotValue = new ModuleMethod(frame2, 55, Lit127, 8194);
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
        Screen1 = null;
        this.form$Mnname$Mnsymbol = Lit0;
        this.events$Mnto$Mnregister = LList.Empty;
        this.components$Mnto$Mncreate = LList.Empty;
        this.global$Mnvars$Mnto$Mncreate = LList.Empty;
        this.form$Mndo$Mnafter$Mncreation = LList.Empty;
        C0718runtime.$instance.run();
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addGlobalVarToCurrentFormEnvironment(Lit3, ""), $result);
        } else {
            addToGlobalVars(Lit3, lambda$Fn2);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit4, Boolean.TRUE, Lit5);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit6, Lit7, Lit8);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit9, "UniHawk - Immobilizer", Lit10);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit11, Lit12, Lit8);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit13, "slidevertical", Lit10);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit14, "TelaAPP.PNG", Lit10);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit15, "slidevertical", Lit10);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit16, "user", Lit10);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit17, Boolean.TRUE, Lit5);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit18, Boolean.TRUE, Lit5);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit19, "Responsive", Lit10);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit20, "AppTheme", Lit10);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit21, "Login", Lit10);
            Values.writeValues(C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit22, Boolean.FALSE, Lit5), $result);
        } else {
            addToFormDoAfterCreation(new Promise(lambda$Fn3));
        }
        this.f419OrganizaoVertical2 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit23, Lit24, lambda$Fn4), $result);
        } else {
            addToComponents(Lit0, Lit27, Lit24, lambda$Fn5);
        }
        this.txt_Titulo = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit28, Lit29, lambda$Fn6), $result);
        } else {
            addToComponents(Lit0, Lit36, Lit29, lambda$Fn7);
        }
        this.HorizontalArrangement4 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit37, Lit38, lambda$Fn8), $result);
        } else {
            addToComponents(Lit0, Lit39, Lit38, lambda$Fn9);
        }
        this.txt_login = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit40, Lit41, lambda$Fn10), $result);
        } else {
            addToComponents(Lit0, Lit43, Lit41, lambda$Fn11);
        }
        this.HorizontalArrangement1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit44, Lit45, lambda$Fn12), $result);
        } else {
            addToComponents(Lit0, Lit47, Lit45, lambda$Fn13);
        }
        this.cx_login = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit48, Lit49, lambda$Fn14), $result);
        } else {
            addToComponents(Lit0, Lit56, Lit49, lambda$Fn15);
        }
        this.HorizontalArrangement3 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit57, Lit58, lambda$Fn16), $result);
        } else {
            addToComponents(Lit0, Lit59, Lit58, lambda$Fn17);
        }
        this.txt_senha = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit60, Lit61, lambda$Fn18), $result);
        } else {
            addToComponents(Lit0, Lit63, Lit61, lambda$Fn19);
        }
        this.HorizontalArrangement2 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit64, Lit65, lambda$Fn20), $result);
        } else {
            addToComponents(Lit0, Lit66, Lit65, lambda$Fn21);
        }
        this.cx_senha = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit67, Lit68, lambda$Fn22), $result);
        } else {
            addToComponents(Lit0, Lit69, Lit68, lambda$Fn23);
        }
        this.f418OrganizaoVertical1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit70, Lit71, Boolean.FALSE), $result);
        } else {
            addToComponents(Lit0, Lit72, Lit71, Boolean.FALSE);
        }
        this.btn_entrar = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit73, Lit74, lambda$Fn24), $result);
        } else {
            addToComponents(Lit0, Lit78, Lit74, lambda$Fn25);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit89, this.btn_entrar$Click);
        } else {
            addToFormEnvironment(Lit89, this.btn_entrar$Click);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "btn_entrar", "Click");
        } else {
            addToEvents(Lit74, Lit90);
        }
        this.btn_cadastrar = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit91, Lit92, lambda$Fn26), $result);
        } else {
            addToComponents(Lit0, Lit96, Lit92, lambda$Fn27);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit98, this.btn_cadastrar$Click);
        } else {
            addToFormEnvironment(Lit98, this.btn_cadastrar$Click);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "btn_cadastrar", "Click");
        } else {
            addToEvents(Lit92, Lit90);
        }
        this.btn_esqueciSenha = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit99, Lit100, lambda$Fn28), $result);
        } else {
            addToComponents(Lit0, Lit105, Lit100, lambda$Fn29);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit107, this.btn_esqueciSenha$Click);
        } else {
            addToFormEnvironment(Lit107, this.btn_esqueciSenha$Click);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "btn_esqueciSenha", "Click");
        } else {
            addToEvents(Lit100, Lit90);
        }
        this.Notificador1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit108, Lit109, Boolean.FALSE), $result);
        } else {
            addToComponents(Lit0, Lit110, Lit109, Boolean.FALSE);
        }
        this.FirebaseDB1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit111, Lit79, lambda$Fn30), $result);
        } else {
            addToComponents(Lit0, Lit117, Lit79, lambda$Fn31);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit127, this.FirebaseDB1$GotValue);
        } else {
            addToFormEnvironment(Lit127, this.FirebaseDB1$GotValue);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "FirebaseDB1", "GotValue");
        } else {
            addToEvents(Lit79, Lit128);
        }
        C0718runtime.initRuntime();
    }

    static String lambda3() {
        return "";
    }

    static Object lambda4() {
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit4, Boolean.TRUE, Lit5);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit6, Lit7, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit9, "UniHawk - Immobilizer", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit11, Lit12, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit13, "slidevertical", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit14, "TelaAPP.PNG", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit15, "slidevertical", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit16, "user", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit17, Boolean.TRUE, Lit5);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit18, Boolean.TRUE, Lit5);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit19, "Responsive", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit20, "AppTheme", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit21, "Login", Lit10);
        return C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit22, Boolean.FALSE, Lit5);
    }

    static Object lambda5() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit24, Lit25, Lit26, Lit8);
    }

    static Object lambda6() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit24, Lit25, Lit26, Lit8);
    }

    static Object lambda7() {
        C0718runtime.setAndCoerceProperty$Ex(Lit29, Lit30, Boolean.TRUE, Lit5);
        C0718runtime.setAndCoerceProperty$Ex(Lit29, Lit31, Lit32, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit29, Lit33, "Digite seu login e senha", Lit10);
        return C0718runtime.setAndCoerceProperty$Ex(Lit29, Lit34, Lit35, Lit8);
    }

    static Object lambda8() {
        C0718runtime.setAndCoerceProperty$Ex(Lit29, Lit30, Boolean.TRUE, Lit5);
        C0718runtime.setAndCoerceProperty$Ex(Lit29, Lit31, Lit32, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit29, Lit33, "Digite seu login e senha", Lit10);
        return C0718runtime.setAndCoerceProperty$Ex(Lit29, Lit34, Lit35, Lit8);
    }

    static Object lambda10() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit38, Lit25, Lit26, Lit8);
    }

    static Object lambda9() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit38, Lit25, Lit26, Lit8);
    }

    static Object lambda11() {
        C0718runtime.setAndCoerceProperty$Ex(Lit41, Lit31, Lit26, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit41, Lit33, "Login", Lit10);
        return C0718runtime.setAndCoerceProperty$Ex(Lit41, Lit34, Lit42, Lit8);
    }

    static Object lambda12() {
        C0718runtime.setAndCoerceProperty$Ex(Lit41, Lit31, Lit26, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit41, Lit33, "Login", Lit10);
        return C0718runtime.setAndCoerceProperty$Ex(Lit41, Lit34, Lit42, Lit8);
    }

    static Object lambda13() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit45, Lit25, Lit46, Lit8);
    }

    static Object lambda14() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit45, Lit25, Lit46, Lit8);
    }

    static Object lambda15() {
        C0718runtime.setAndCoerceProperty$Ex(Lit49, Lit31, Lit50, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit49, Lit51, "E-mail", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit49, Lit52, Lit53, Lit8);
        return C0718runtime.setAndCoerceProperty$Ex(Lit49, Lit54, Lit55, Lit8);
    }

    static Object lambda16() {
        C0718runtime.setAndCoerceProperty$Ex(Lit49, Lit31, Lit50, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit49, Lit51, "E-mail", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit49, Lit52, Lit53, Lit8);
        return C0718runtime.setAndCoerceProperty$Ex(Lit49, Lit54, Lit55, Lit8);
    }

    static Object lambda17() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit58, Lit25, Lit50, Lit8);
    }

    static Object lambda18() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit58, Lit25, Lit50, Lit8);
    }

    static Object lambda19() {
        C0718runtime.setAndCoerceProperty$Ex(Lit61, Lit31, Lit26, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit61, Lit33, "Senha", Lit10);
        return C0718runtime.setAndCoerceProperty$Ex(Lit61, Lit34, Lit62, Lit8);
    }

    static Object lambda20() {
        C0718runtime.setAndCoerceProperty$Ex(Lit61, Lit31, Lit26, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit61, Lit33, "Senha", Lit10);
        return C0718runtime.setAndCoerceProperty$Ex(Lit61, Lit34, Lit62, Lit8);
    }

    static Object lambda21() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit65, Lit25, Lit46, Lit8);
    }

    static Object lambda22() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit65, Lit25, Lit46, Lit8);
    }

    static Object lambda23() {
        C0718runtime.setAndCoerceProperty$Ex(Lit68, Lit31, Lit50, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit68, Lit51, "*************", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit68, Lit52, Lit53, Lit8);
        return C0718runtime.setAndCoerceProperty$Ex(Lit68, Lit54, Lit55, Lit8);
    }

    static Object lambda24() {
        C0718runtime.setAndCoerceProperty$Ex(Lit68, Lit31, Lit50, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit68, Lit51, "*************", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit68, Lit52, Lit53, Lit8);
        return C0718runtime.setAndCoerceProperty$Ex(Lit68, Lit54, Lit55, Lit8);
    }

    static Object lambda25() {
        C0718runtime.setAndCoerceProperty$Ex(Lit74, Lit11, Lit75, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit74, Lit31, Lit26, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit74, Lit33, "Entrar", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit74, Lit34, Lit76, Lit8);
        return C0718runtime.setAndCoerceProperty$Ex(Lit74, Lit54, Lit77, Lit8);
    }

    static Object lambda26() {
        C0718runtime.setAndCoerceProperty$Ex(Lit74, Lit11, Lit75, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit74, Lit31, Lit26, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit74, Lit33, "Entrar", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit74, Lit34, Lit76, Lit8);
        return C0718runtime.setAndCoerceProperty$Ex(Lit74, Lit54, Lit77, Lit8);
    }

    public Object btn_entrar$Click() {
        C0718runtime.setThisForm();
        C0718runtime.callComponentMethod(Lit79, Lit80, LList.list2(C0718runtime.callYailPrimitive(C0718runtime.string$Mnreplace$Mnall, LList.list3(C0718runtime.get$Mnproperty.apply2(Lit49, Lit33), ".", "_"), Lit81, "replace all"), C0718runtime.callYailPrimitive(C0718runtime.make$Mnyail$Mnlist, LList.list4(C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit53, Lit82), Lit83, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit53, Lit82), Lit84, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit53, Lit82), Lit85, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit53, Lit82), Lit86, "random integer")), Lit87, "make a list")), Lit88);
        return C0718runtime.addGlobalVarToCurrentFormEnvironment(Lit3, C0718runtime.get$Mnproperty.apply2(Lit49, Lit33));
    }

    static Object lambda27() {
        C0718runtime.setAndCoerceProperty$Ex(Lit92, Lit11, Lit93, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit92, Lit31, Lit26, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit92, Lit33, "Cadastrar", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit92, Lit34, Lit94, Lit8);
        return C0718runtime.setAndCoerceProperty$Ex(Lit92, Lit54, Lit95, Lit8);
    }

    static Object lambda28() {
        C0718runtime.setAndCoerceProperty$Ex(Lit92, Lit11, Lit93, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit92, Lit31, Lit26, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit92, Lit33, "Cadastrar", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit92, Lit34, Lit94, Lit8);
        return C0718runtime.setAndCoerceProperty$Ex(Lit92, Lit54, Lit95, Lit8);
    }

    public Object btn_cadastrar$Click() {
        C0718runtime.setThisForm();
        return C0718runtime.callYailPrimitive(C0718runtime.open$Mnanother$Mnscreen, LList.list1("cadastrar_usuario"), Lit97, "open another screen");
    }

    static Object lambda29() {
        C0718runtime.setAndCoerceProperty$Ex(Lit100, Lit11, Lit101, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit100, Lit102, Boolean.TRUE, Lit5);
        C0718runtime.setAndCoerceProperty$Ex(Lit100, Lit31, Lit103, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit100, Lit33, "Esqueci minha senha", Lit10);
        return C0718runtime.setAndCoerceProperty$Ex(Lit100, Lit34, Lit104, Lit8);
    }

    static Object lambda30() {
        C0718runtime.setAndCoerceProperty$Ex(Lit100, Lit11, Lit101, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit100, Lit102, Boolean.TRUE, Lit5);
        C0718runtime.setAndCoerceProperty$Ex(Lit100, Lit31, Lit103, Lit8);
        C0718runtime.setAndCoerceProperty$Ex(Lit100, Lit33, "Esqueci minha senha", Lit10);
        return C0718runtime.setAndCoerceProperty$Ex(Lit100, Lit34, Lit104, Lit8);
    }

    public Object btn_esqueciSenha$Click() {
        C0718runtime.setThisForm();
        return C0718runtime.callYailPrimitive(C0718runtime.open$Mnanother$Mnscreen, LList.list1("TesteTrocaSenha"), Lit106, "open another screen");
    }

    static Object lambda31() {
        C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit112, "https://dazzling-fire-7140.firebaseio.com/", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit113, "eva:tcctelemetria@gmail:com/", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit114, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkIjp7InVpZCI6IjZkMDNiMTQ4LWEzM2ItNDFlNy1iYzJmLWUxNDE3MzAxNWMwNCIsInByb2plY3QiOiJFVkFfaW1vYmlsaXplciIsImRldmVsb3BlciI6ImV2YTp0Y2N0ZWxlbWV0cmlhQGdtYWlsOmNvbSJ9LCJ2IjowLCJleHAiOjE2NzQ1NzAyMTI2LCJpYXQiOjE2NjE0NzE3MjZ9.taJEvpUuf8xCJGedBTYJB3otp_XLJUZBqwAIX6e7lN0", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit115, "https://unihawk-immobilizer-v1-default-rtdb.firebaseio.com/", Lit10);
        return C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit116, "EVA_imobilizer", Lit10);
    }

    static Object lambda32() {
        C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit112, "https://dazzling-fire-7140.firebaseio.com/", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit113, "eva:tcctelemetria@gmail:com/", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit114, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkIjp7InVpZCI6IjZkMDNiMTQ4LWEzM2ItNDFlNy1iYzJmLWUxNDE3MzAxNWMwNCIsInByb2plY3QiOiJFVkFfaW1vYmlsaXplciIsImRldmVsb3BlciI6ImV2YTp0Y2N0ZWxlbWV0cmlhQGdtYWlsOmNvbSJ9LCJ2IjowLCJleHAiOjE2NzQ1NzAyMTI2LCJpYXQiOjE2NjE0NzE3MjZ9.taJEvpUuf8xCJGedBTYJB3otp_XLJUZBqwAIX6e7lN0", Lit10);
        C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit115, "https://unihawk-immobilizer-v1-default-rtdb.firebaseio.com/", Lit10);
        return C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit116, "EVA_imobilizer", Lit10);
    }

    /* compiled from: Screen1.yail */
    public class frame0 extends ModuleBody {
        Object $value;
        final ModuleMethod lambda$Fn32 = new ModuleMethod(this, 1, (Object) null, 0);
        final ModuleMethod lambda$Fn33 = new ModuleMethod(this, 2, (Object) null, 0);

        public Object apply0(ModuleMethod moduleMethod) {
            switch (moduleMethod.selector) {
                case 1:
                    return lambda33();
                case 2:
                    return lambda34();
                default:
                    return super.apply0(moduleMethod);
            }
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 1:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 2:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                default:
                    return super.match0(moduleMethod, callContext);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda33() {
            Object obj;
            ModuleMethod moduleMethod = C0718runtime.yail$Mnequal$Qu;
            ModuleMethod moduleMethod2 = C0718runtime.yail$Mnlist$Mnget$Mnitem;
            if (this.$value instanceof Package) {
                obj = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Screen1.Lit118), " is not bound in the current context"), "Unbound Variable");
            } else {
                obj = this.$value;
            }
            return C0718runtime.callYailPrimitive(moduleMethod, LList.list2(C0718runtime.callYailPrimitive(moduleMethod2, LList.list2(obj, Screen1.Lit53), Screen1.Lit119, "select list item"), C0718runtime.callYailPrimitive(C0718runtime.string$Mnreplace$Mnall, LList.list3(C0718runtime.get$Mnproperty.apply2(Screen1.Lit49, Screen1.Lit33), ".", "_"), Screen1.Lit120, "replace all")), Screen1.Lit121, "=");
        }

        /* access modifiers changed from: package-private */
        public Object lambda34() {
            Object obj;
            ModuleMethod moduleMethod = C0718runtime.yail$Mnequal$Qu;
            ModuleMethod moduleMethod2 = C0718runtime.yail$Mnlist$Mnget$Mnitem;
            if (this.$value instanceof Package) {
                obj = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Screen1.Lit118), " is not bound in the current context"), "Unbound Variable");
            } else {
                obj = this.$value;
            }
            return C0718runtime.callYailPrimitive(moduleMethod, LList.list2(C0718runtime.callYailPrimitive(moduleMethod2, LList.list2(obj, Screen1.Lit46), Screen1.Lit122, "select list item"), C0718runtime.get$Mnproperty.apply2(Screen1.Lit68, Screen1.Lit33)), Screen1.Lit123, "=");
        }
    }

    public Object FirebaseDB1$GotValue(Object $tag, Object $value) {
        frame0 frame02 = new frame0();
        C0718runtime.sanitizeComponentData($tag);
        frame02.$value = C0718runtime.sanitizeComponentData($value);
        C0718runtime.setThisForm();
        return C0718runtime.processAndDelayed$V(new Object[]{frame02.lambda$Fn32, frame02.lambda$Fn33}) != Boolean.FALSE ? C0718runtime.callYailPrimitive(C0718runtime.open$Mnanother$Mnscreen$Mnwith$Mnstart$Mnvalue, LList.list2("Maquinario", C0718runtime.lookupGlobalVarInCurrentFormEnvironment(Lit3, C0718runtime.$Stthe$Mnnull$Mnvalue$St)), Lit124, "open another screen with start value") : C0718runtime.callComponentMethod(Lit109, Lit125, LList.list1("Senha ou usuário incorreto"), Lit126);
    }

    /* compiled from: Screen1.yail */
    public class frame extends ModuleBody {
        Screen1 $main;

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 3:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 4:
                    if (!(obj instanceof Screen1)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 5:
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
                case 9:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 14:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 15:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 16:
                    if (!(obj instanceof Screen1)) {
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
                case 6:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 2;
                    return 0;
                case 7:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 2;
                    return 0;
                case 10:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
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
                case 13:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 2;
                    return 0;
                case 19:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 2;
                    return 0;
                case 55:
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
                case 12:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.value4 = obj4;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 4;
                    return 0;
                case 17:
                    if (!(obj instanceof Screen1)) {
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
                case 18:
                    if (!(obj instanceof Screen1)) {
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
                case 3:
                    return this.$main.getSimpleName(obj);
                case 4:
                    try {
                        this.$main.onCreate((Bundle) obj);
                        return Values.empty;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "onCreate", 1, obj);
                    }
                case 5:
                    this.$main.androidLogForm(obj);
                    return Values.empty;
                case 7:
                    try {
                        return this.$main.lookupInFormEnvironment((Symbol) obj);
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "lookup-in-form-environment", 1, obj);
                    }
                case 9:
                    try {
                        return this.$main.isBoundInFormEnvironment((Symbol) obj) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "is-bound-in-form-environment", 1, obj);
                    }
                case 14:
                    this.$main.addToFormDoAfterCreation(obj);
                    return Values.empty;
                case 15:
                    this.$main.sendError(obj);
                    return Values.empty;
                case 16:
                    this.$main.processException(obj);
                    return Values.empty;
                default:
                    return super.apply1(moduleMethod, obj);
            }
        }

        public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
            boolean z = true;
            switch (moduleMethod.selector) {
                case 12:
                    this.$main.addToComponents(obj, obj2, obj3, obj4);
                    return Values.empty;
                case 17:
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
                case 18:
                    Screen1 screen1 = this.$main;
                    try {
                        Component component = (Component) obj;
                        try {
                            String str = (String) obj2;
                            try {
                                if (obj3 == Boolean.FALSE) {
                                    z = false;
                                }
                                try {
                                    screen1.dispatchGenericEvent(component, str, z, (Object[]) obj4);
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
                case 6:
                    try {
                        this.$main.addToFormEnvironment((Symbol) obj, obj2);
                        return Values.empty;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "add-to-form-environment", 1, obj);
                    }
                case 7:
                    try {
                        return this.$main.lookupInFormEnvironment((Symbol) obj, obj2);
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "lookup-in-form-environment", 1, obj);
                    }
                case 10:
                    try {
                        this.$main.addToGlobalVarEnvironment((Symbol) obj, obj2);
                        return Values.empty;
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "add-to-global-var-environment", 1, obj);
                    }
                case 11:
                    this.$main.addToEvents(obj, obj2);
                    return Values.empty;
                case 13:
                    this.$main.addToGlobalVars(obj, obj2);
                    return Values.empty;
                case 19:
                    return this.$main.lookupHandler(obj, obj2);
                case 55:
                    return this.$main.FirebaseDB1$GotValue(obj, obj2);
                default:
                    return super.apply2(moduleMethod, obj, obj2);
            }
        }

        public Object apply0(ModuleMethod moduleMethod) {
            switch (moduleMethod.selector) {
                case 20:
                    return Screen1.lambda2();
                case 21:
                    this.$main.$define();
                    return Values.empty;
                case 22:
                    return Screen1.lambda3();
                case 23:
                    return Screen1.lambda4();
                case 24:
                    return Screen1.lambda5();
                case 25:
                    return Screen1.lambda6();
                case 26:
                    return Screen1.lambda7();
                case 27:
                    return Screen1.lambda8();
                case 28:
                    return Screen1.lambda9();
                case 29:
                    return Screen1.lambda10();
                case 30:
                    return Screen1.lambda11();
                case 31:
                    return Screen1.lambda12();
                case 32:
                    return Screen1.lambda13();
                case 33:
                    return Screen1.lambda14();
                case 34:
                    return Screen1.lambda15();
                case 35:
                    return Screen1.lambda16();
                case 36:
                    return Screen1.lambda17();
                case 37:
                    return Screen1.lambda18();
                case 38:
                    return Screen1.lambda19();
                case 39:
                    return Screen1.lambda20();
                case 40:
                    return Screen1.lambda21();
                case 41:
                    return Screen1.lambda22();
                case 42:
                    return Screen1.lambda23();
                case 43:
                    return Screen1.lambda24();
                case 44:
                    return Screen1.lambda25();
                case 45:
                    return Screen1.lambda26();
                case 46:
                    return this.$main.btn_entrar$Click();
                case 47:
                    return Screen1.lambda27();
                case 48:
                    return Screen1.lambda28();
                case 49:
                    return this.$main.btn_cadastrar$Click();
                case 50:
                    return Screen1.lambda29();
                case 51:
                    return Screen1.lambda30();
                case 52:
                    return this.$main.btn_esqueciSenha$Click();
                case 53:
                    return Screen1.lambda31();
                case 54:
                    return Screen1.lambda32();
                default:
                    return super.apply0(moduleMethod);
            }
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            switch (moduleMethod.selector) {
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
        Screen1 = this;
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
