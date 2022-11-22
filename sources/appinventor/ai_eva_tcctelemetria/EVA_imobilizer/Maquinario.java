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
import com.google.appinventor.components.runtime.HorizontalScrollArrangement;
import com.google.appinventor.components.runtime.Image;
import com.google.appinventor.components.runtime.Label;
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

/* compiled from: Maquinario.yail */
public class Maquinario extends Form implements Runnable {
    static final SimpleSymbol Lit0 = ((SimpleSymbol) new SimpleSymbol("Maquinario").readResolve());
    static final SimpleSymbol Lit1 = ((SimpleSymbol) new SimpleSymbol("getMessage").readResolve());
    static final SimpleSymbol Lit10 = ((SimpleSymbol) new SimpleSymbol("ActionBar").readResolve());
    static final SimpleSymbol Lit100 = ((SimpleSymbol) new SimpleSymbol("FontSize").readResolve());
    static final IntNum Lit101 = IntNum.make(20);
    static final SimpleSymbol Lit102 = ((SimpleSymbol) new SimpleSymbol("TextAlignment").readResolve());
    static final IntNum Lit103;
    static final FString Lit104 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit105 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit106 = ((SimpleSymbol) new SimpleSymbol("btn_acessar1").readResolve());
    static final IntNum Lit107;
    static final SimpleSymbol Lit108 = ((SimpleSymbol) new SimpleSymbol("FontBold").readResolve());
    static final IntNum Lit109 = IntNum.make(-1006);
    static final SimpleSymbol Lit11 = ((SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN).readResolve());
    static final IntNum Lit110;
    static final IntNum Lit111 = IntNum.make(-1035);
    static final FString Lit112 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit113 = PairWithPosition.make(Lit207, PairWithPosition.make(Lit207, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 635072), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 635067);
    static final PairWithPosition Lit114 = PairWithPosition.make(Lit13, PairWithPosition.make(Lit207, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 635101), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 635095);
    static final SimpleSymbol Lit115 = ((SimpleSymbol) new SimpleSymbol("btn_acessar1$Click").readResolve());
    static final FString Lit116 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit117 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal2").readResolve());
    static final IntNum Lit118;
    static final IntNum Lit119 = IntNum.make(-1023);
    static final SimpleSymbol Lit12 = ((SimpleSymbol) new SimpleSymbol("AppName").readResolve());
    static final FString Lit120 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit121 = new FString("com.google.appinventor.components.runtime.Image");
    static final SimpleSymbol Lit122 = ((SimpleSymbol) new SimpleSymbol("img_escavadeira2").readResolve());
    static final IntNum Lit123 = IntNum.make(-1023);
    static final IntNum Lit124 = IntNum.make(-1035);
    static final FString Lit125 = new FString("com.google.appinventor.components.runtime.Image");
    static final FString Lit126 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    static final SimpleSymbol Lit127 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoVertical2").readResolve());
    static final IntNum Lit128;
    static final FString Lit129 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    static final SimpleSymbol Lit13;
    static final FString Lit130 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit131 = ((SimpleSymbol) new SimpleSymbol("txt_tituloMaquina2").readResolve());
    static final IntNum Lit132;
    static final FString Lit133 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit134 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit135 = ((SimpleSymbol) new SimpleSymbol("btn_acessar2").readResolve());
    static final IntNum Lit136;
    static final IntNum Lit137 = IntNum.make(-1006);
    static final IntNum Lit138;
    static final IntNum Lit139 = IntNum.make(-1035);
    static final SimpleSymbol Lit14 = ((SimpleSymbol) new SimpleSymbol("BackgroundColor").readResolve());
    static final FString Lit140 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit141 = PairWithPosition.make(Lit207, PairWithPosition.make(Lit207, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 925888), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 925883);
    static final PairWithPosition Lit142 = PairWithPosition.make(Lit13, PairWithPosition.make(Lit207, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 925917), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 925911);
    static final SimpleSymbol Lit143 = ((SimpleSymbol) new SimpleSymbol("btn_acessar2$Click").readResolve());
    static final FString Lit144 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit145 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal3").readResolve());
    static final IntNum Lit146;
    static final IntNum Lit147 = IntNum.make(-1023);
    static final FString Lit148 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit149 = new FString("com.google.appinventor.components.runtime.Image");
    static final IntNum Lit15;
    static final SimpleSymbol Lit150 = ((SimpleSymbol) new SimpleSymbol("img_escavadeira3").readResolve());
    static final IntNum Lit151 = IntNum.make(-1023);
    static final IntNum Lit152 = IntNum.make(-1035);
    static final FString Lit153 = new FString("com.google.appinventor.components.runtime.Image");
    static final FString Lit154 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    static final SimpleSymbol Lit155 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoVertical3").readResolve());
    static final IntNum Lit156;
    static final FString Lit157 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    static final FString Lit158 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit159 = ((SimpleSymbol) new SimpleSymbol("txt_tituloMaquina3").readResolve());
    static final SimpleSymbol Lit16;
    static final IntNum Lit160;
    static final FString Lit161 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit162 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit163 = ((SimpleSymbol) new SimpleSymbol("btn_acessar3").readResolve());
    static final IntNum Lit164;
    static final IntNum Lit165 = IntNum.make(-1006);
    static final IntNum Lit166;
    static final IntNum Lit167 = IntNum.make(-1035);
    static final FString Lit168 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit169 = PairWithPosition.make(Lit207, PairWithPosition.make(Lit207, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1216704), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1216699);
    static final SimpleSymbol Lit17 = ((SimpleSymbol) new SimpleSymbol("CloseScreenAnimation").readResolve());
    static final PairWithPosition Lit170;
    static final SimpleSymbol Lit171 = ((SimpleSymbol) new SimpleSymbol("btn_acessar3$Click").readResolve());
    static final FString Lit172 = new FString("com.google.appinventor.components.runtime.FirebaseDB");
    static final SimpleSymbol Lit173 = ((SimpleSymbol) new SimpleSymbol("DefaultURL").readResolve());
    static final SimpleSymbol Lit174 = ((SimpleSymbol) new SimpleSymbol("DeveloperBucket").readResolve());
    static final SimpleSymbol Lit175 = ((SimpleSymbol) new SimpleSymbol("FirebaseToken").readResolve());
    static final SimpleSymbol Lit176 = ((SimpleSymbol) new SimpleSymbol("FirebaseURL").readResolve());
    static final SimpleSymbol Lit177 = ((SimpleSymbol) new SimpleSymbol("ProjectBucket").readResolve());
    static final FString Lit178 = new FString("com.google.appinventor.components.runtime.FirebaseDB");
    static final SimpleSymbol Lit179 = ((SimpleSymbol) new SimpleSymbol("$value").readResolve());
    static final SimpleSymbol Lit18 = ((SimpleSymbol) new SimpleSymbol("OpenScreenAnimation").readResolve());
    static final IntNum Lit180 = IntNum.make(6);
    static final PairWithPosition Lit181 = PairWithPosition.make(Lit208, PairWithPosition.make(Lit16, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1286303), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1286297);
    static final PairWithPosition Lit182 = PairWithPosition.make(Lit207, PairWithPosition.make(Lit207, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1286341), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1286336);
    static final PairWithPosition Lit183 = PairWithPosition.make(Lit208, PairWithPosition.make(Lit16, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1286575), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1286569);
    static final IntNum Lit184 = IntNum.make(8);
    static final PairWithPosition Lit185 = PairWithPosition.make(Lit208, PairWithPosition.make(Lit16, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1286765), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1286759);
    static final PairWithPosition Lit186 = PairWithPosition.make(Lit207, PairWithPosition.make(Lit207, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1286803), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1286798);
    static final PairWithPosition Lit187 = PairWithPosition.make(Lit208, PairWithPosition.make(Lit16, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1287038), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1287032);
    static final PairWithPosition Lit188 = PairWithPosition.make(Lit208, PairWithPosition.make(Lit16, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1287229), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1287223);
    static final PairWithPosition Lit189 = PairWithPosition.make(Lit207, PairWithPosition.make(Lit207, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1287267), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1287262);
    static final SimpleSymbol Lit19 = ((SimpleSymbol) new SimpleSymbol("ScreenOrientation").readResolve());
    static final PairWithPosition Lit190;
    static final SimpleSymbol Lit191 = ((SimpleSymbol) new SimpleSymbol("FirebaseDB1$GotValue").readResolve());
    static final SimpleSymbol Lit192 = ((SimpleSymbol) new SimpleSymbol("GotValue").readResolve());
    static final SimpleSymbol Lit193 = ((SimpleSymbol) new SimpleSymbol("get-simple-name").readResolve());
    static final SimpleSymbol Lit194 = ((SimpleSymbol) new SimpleSymbol("android-log-form").readResolve());
    static final SimpleSymbol Lit195 = ((SimpleSymbol) new SimpleSymbol("add-to-form-environment").readResolve());
    static final SimpleSymbol Lit196 = ((SimpleSymbol) new SimpleSymbol("lookup-in-form-environment").readResolve());
    static final SimpleSymbol Lit197 = ((SimpleSymbol) new SimpleSymbol("is-bound-in-form-environment").readResolve());
    static final SimpleSymbol Lit198 = ((SimpleSymbol) new SimpleSymbol("add-to-global-var-environment").readResolve());
    static final SimpleSymbol Lit199 = ((SimpleSymbol) new SimpleSymbol("add-to-events").readResolve());
    static final SimpleSymbol Lit2 = ((SimpleSymbol) new SimpleSymbol("*the-null-value*").readResolve());
    static final SimpleSymbol Lit20 = ((SimpleSymbol) new SimpleSymbol("Scrollable").readResolve());
    static final SimpleSymbol Lit200 = ((SimpleSymbol) new SimpleSymbol("add-to-components").readResolve());
    static final SimpleSymbol Lit201 = ((SimpleSymbol) new SimpleSymbol("add-to-global-vars").readResolve());
    static final SimpleSymbol Lit202 = ((SimpleSymbol) new SimpleSymbol("add-to-form-do-after-creation").readResolve());
    static final SimpleSymbol Lit203 = ((SimpleSymbol) new SimpleSymbol("send-error").readResolve());
    static final SimpleSymbol Lit204 = ((SimpleSymbol) new SimpleSymbol("dispatchEvent").readResolve());
    static final SimpleSymbol Lit205 = ((SimpleSymbol) new SimpleSymbol("dispatchGenericEvent").readResolve());
    static final SimpleSymbol Lit206 = ((SimpleSymbol) new SimpleSymbol("lookup-handler").readResolve());
    static final SimpleSymbol Lit207 = ((SimpleSymbol) new SimpleSymbol("any").readResolve());
    static final SimpleSymbol Lit208 = ((SimpleSymbol) new SimpleSymbol("list").readResolve());
    static final SimpleSymbol Lit21 = ((SimpleSymbol) new SimpleSymbol("ShowListsAsJson").readResolve());
    static final SimpleSymbol Lit22 = ((SimpleSymbol) new SimpleSymbol("Sizing").readResolve());
    static final SimpleSymbol Lit23 = ((SimpleSymbol) new SimpleSymbol("Theme").readResolve());
    static final SimpleSymbol Lit24 = ((SimpleSymbol) new SimpleSymbol("Title").readResolve());
    static final SimpleSymbol Lit25 = ((SimpleSymbol) new SimpleSymbol("TitleVisible").readResolve());
    static final SimpleSymbol Lit26 = ((SimpleSymbol) new SimpleSymbol("FirebaseDB1").readResolve());
    static final SimpleSymbol Lit27 = ((SimpleSymbol) new SimpleSymbol("GetValue").readResolve());
    static final PairWithPosition Lit28 = PairWithPosition.make(Lit13, PairWithPosition.make(Lit13, PairWithPosition.make(Lit13, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 118955), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 118950), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 118944);
    static final IntNum Lit29 = IntNum.make(100);
    static final SimpleSymbol Lit3 = ((SimpleSymbol) new SimpleSymbol("g$acesso").readResolve());
    static final PairWithPosition Lit30 = PairWithPosition.make(Lit16, PairWithPosition.make(Lit16, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 119105), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 119097);
    static final PairWithPosition Lit31 = PairWithPosition.make(Lit16, PairWithPosition.make(Lit16, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 119203), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 119195);
    static final PairWithPosition Lit32 = PairWithPosition.make(Lit16, PairWithPosition.make(Lit16, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 119301), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 119293);
    static final PairWithPosition Lit33 = PairWithPosition.make(Lit16, PairWithPosition.make(Lit16, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 119399), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 119391);
    static final PairWithPosition Lit34 = PairWithPosition.make(Lit16, PairWithPosition.make(Lit16, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 119497), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 119489);
    static final PairWithPosition Lit35 = PairWithPosition.make(Lit16, PairWithPosition.make(Lit16, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 119595), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 119587);
    static final PairWithPosition Lit36 = PairWithPosition.make(Lit16, PairWithPosition.make(Lit16, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 119693), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 119685);
    static final PairWithPosition Lit37 = PairWithPosition.make(Lit16, PairWithPosition.make(Lit16, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 119791), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 119783);
    static final PairWithPosition Lit38 = PairWithPosition.make(Lit16, PairWithPosition.make(Lit16, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 119889), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 119881);
    static final PairWithPosition Lit39 = PairWithPosition.make(Lit16, PairWithPosition.make(Lit16, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 119987), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 119979);
    static final SimpleSymbol Lit4 = ((SimpleSymbol) new SimpleSymbol("g$m1").readResolve());
    static final PairWithPosition Lit40 = PairWithPosition.make(Lit16, PairWithPosition.make(Lit16, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 120085), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 120077);
    static final PairWithPosition Lit41 = PairWithPosition.make(Lit207, PairWithPosition.make(Lit207, PairWithPosition.make(Lit207, PairWithPosition.make(Lit207, PairWithPosition.make(Lit207, PairWithPosition.make(Lit207, PairWithPosition.make(Lit207, PairWithPosition.make(Lit207, PairWithPosition.make(Lit207, PairWithPosition.make(Lit207, PairWithPosition.make(Lit207, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 120155), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 120151), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 120147), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 120143), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 120139), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 120135), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 120131), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 120127), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 120123), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 120119), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 120114);
    static final PairWithPosition Lit42 = PairWithPosition.make(Lit13, PairWithPosition.make(Lit207, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 120184), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 120178);
    static final SimpleSymbol Lit43 = ((SimpleSymbol) new SimpleSymbol("Maquinario$Initialize").readResolve());
    static final SimpleSymbol Lit44 = ((SimpleSymbol) new SimpleSymbol("Initialize").readResolve());
    static final FString Lit45 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit46 = ((SimpleSymbol) new SimpleSymbol("Legenda1").readResolve());
    static final SimpleSymbol Lit47 = ((SimpleSymbol) new SimpleSymbol("Height").readResolve());
    static final IntNum Lit48 = IntNum.make(-1001);
    static final SimpleSymbol Lit49 = ((SimpleSymbol) new SimpleSymbol("TextColor").readResolve());
    static final IntNum Lit5 = IntNum.make(1);
    static final IntNum Lit50 = IntNum.make(16777215);
    static final FString Lit51 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit52 = new FString("com.google.appinventor.components.runtime.HorizontalScrollArrangement");
    static final SimpleSymbol Lit53 = ((SimpleSymbol) new SimpleSymbol("HorizontalScrollArrangement1").readResolve());
    static final SimpleSymbol Lit54 = ((SimpleSymbol) new SimpleSymbol("AlignVertical").readResolve());
    static final IntNum Lit55;
    static final SimpleSymbol Lit56 = ((SimpleSymbol) new SimpleSymbol("Width").readResolve());
    static final IntNum Lit57 = IntNum.make(-2);
    static final FString Lit58 = new FString("com.google.appinventor.components.runtime.HorizontalScrollArrangement");
    static final FString Lit59 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit6 = ((SimpleSymbol) new SimpleSymbol("g$m2").readResolve());
    static final SimpleSymbol Lit60 = ((SimpleSymbol) new SimpleSymbol("btn_voltarTela").readResolve());
    static final IntNum Lit61 = IntNum.make(16777215);
    static final IntNum Lit62 = IntNum.make(-1005);
    static final SimpleSymbol Lit63 = ((SimpleSymbol) new SimpleSymbol(Component.LISTVIEW_KEY_IMAGE).readResolve());
    static final IntNum Lit64 = IntNum.make(16777215);
    static final IntNum Lit65 = IntNum.make(-1010);
    static final FString Lit66 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit67 = PairWithPosition.make(Lit13, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 270414);
    static final SimpleSymbol Lit68 = ((SimpleSymbol) new SimpleSymbol("btn_voltarTela$Click").readResolve());
    static final SimpleSymbol Lit69 = ((SimpleSymbol) new SimpleSymbol("Click").readResolve());
    static final IntNum Lit7 = IntNum.make(2);
    static final FString Lit70 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit71 = ((SimpleSymbol) new SimpleSymbol("HorizontalArrangement1").readResolve());
    static final IntNum Lit72 = IntNum.make(10);
    static final IntNum Lit73 = IntNum.make(-1050);
    static final FString Lit74 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit75 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit76 = ((SimpleSymbol) new SimpleSymbol("btn_cadastarMaquina").readResolve());
    static final SimpleSymbol Lit77 = ((SimpleSymbol) new SimpleSymbol("Text").readResolve());
    static final FString Lit78 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit79 = PairWithPosition.make(Lit13, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 344151);
    static final SimpleSymbol Lit8 = ((SimpleSymbol) new SimpleSymbol("g$m3").readResolve());
    static final SimpleSymbol Lit80 = ((SimpleSymbol) new SimpleSymbol("btn_cadastarMaquina$Click").readResolve());
    static final FString Lit81 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit82 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal1").readResolve());
    static final IntNum Lit83;
    static final IntNum Lit84 = IntNum.make(-1023);
    static final SimpleSymbol Lit85 = ((SimpleSymbol) new SimpleSymbol("Visible").readResolve());
    static final FString Lit86 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit87 = new FString("com.google.appinventor.components.runtime.Image");
    static final SimpleSymbol Lit88 = ((SimpleSymbol) new SimpleSymbol("img_escavadeira").readResolve());
    static final IntNum Lit89 = IntNum.make(-1023);
    static final IntNum Lit9 = IntNum.make(3);
    static final SimpleSymbol Lit90 = ((SimpleSymbol) new SimpleSymbol("Picture").readResolve());
    static final IntNum Lit91 = IntNum.make(-1035);
    static final FString Lit92 = new FString("com.google.appinventor.components.runtime.Image");
    static final FString Lit93 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    static final SimpleSymbol Lit94 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoVertical1").readResolve());
    static final SimpleSymbol Lit95 = ((SimpleSymbol) new SimpleSymbol("AlignHorizontal").readResolve());
    static final IntNum Lit96;
    static final FString Lit97 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    static final FString Lit98 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit99 = ((SimpleSymbol) new SimpleSymbol("txt_tituloMaquina").readResolve());
    public static Maquinario Maquinario;
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
    static final ModuleMethod lambda$Fn45 = null;
    static final ModuleMethod lambda$Fn46 = null;
    static final ModuleMethod lambda$Fn47 = null;
    static final ModuleMethod lambda$Fn48 = null;
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
    public HorizontalScrollArrangement HorizontalScrollArrangement1;
    public Label Legenda1;
    public final ModuleMethod Maquinario$Initialize;

    /* renamed from: OrganizaçãoHorizontal1  reason: contains not printable characters */
    public HorizontalArrangement f412OrganizaoHorizontal1;

    /* renamed from: OrganizaçãoHorizontal2  reason: contains not printable characters */
    public HorizontalArrangement f413OrganizaoHorizontal2;

    /* renamed from: OrganizaçãoHorizontal3  reason: contains not printable characters */
    public HorizontalArrangement f414OrganizaoHorizontal3;

    /* renamed from: OrganizaçãoVertical1  reason: contains not printable characters */
    public VerticalArrangement f415OrganizaoVertical1;

    /* renamed from: OrganizaçãoVertical2  reason: contains not printable characters */
    public VerticalArrangement f416OrganizaoVertical2;

    /* renamed from: OrganizaçãoVertical3  reason: contains not printable characters */
    public VerticalArrangement f417OrganizaoVertical3;
    public final ModuleMethod add$Mnto$Mncomponents;
    public final ModuleMethod add$Mnto$Mnevents;
    public final ModuleMethod add$Mnto$Mnform$Mndo$Mnafter$Mncreation;
    public final ModuleMethod add$Mnto$Mnform$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvar$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvars;
    public final ModuleMethod android$Mnlog$Mnform;
    public Button btn_acessar1;
    public final ModuleMethod btn_acessar1$Click;
    public Button btn_acessar2;
    public final ModuleMethod btn_acessar2$Click;
    public Button btn_acessar3;
    public final ModuleMethod btn_acessar3$Click;
    public Button btn_cadastarMaquina;
    public final ModuleMethod btn_cadastarMaquina$Click;
    public Button btn_voltarTela;
    public final ModuleMethod btn_voltarTela$Click;
    public LList components$Mnto$Mncreate;
    public final ModuleMethod dispatchEvent;
    public final ModuleMethod dispatchGenericEvent;
    public LList events$Mnto$Mnregister;
    public LList form$Mndo$Mnafter$Mncreation;
    public Environment form$Mnenvironment;
    public Symbol form$Mnname$Mnsymbol;
    public final ModuleMethod get$Mnsimple$Mnname;
    public Environment global$Mnvar$Mnenvironment;
    public LList global$Mnvars$Mnto$Mncreate;
    public Image img_escavadeira;
    public Image img_escavadeira2;
    public Image img_escavadeira3;
    public final ModuleMethod is$Mnbound$Mnin$Mnform$Mnenvironment;
    public final ModuleMethod lookup$Mnhandler;
    public final ModuleMethod lookup$Mnin$Mnform$Mnenvironment;
    public final ModuleMethod onCreate;
    public final ModuleMethod process$Mnexception;
    public final ModuleMethod send$Mnerror;
    public Label txt_tituloMaquina;
    public Label txt_tituloMaquina2;
    public Label txt_tituloMaquina3;

    static {
        SimpleSymbol simpleSymbol = Lit208;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("number").readResolve();
        Lit16 = simpleSymbol2;
        Lit190 = PairWithPosition.make(simpleSymbol, PairWithPosition.make(simpleSymbol2, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1287503), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1287497);
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_TEXT).readResolve();
        Lit13 = simpleSymbol3;
        Lit170 = PairWithPosition.make(simpleSymbol3, PairWithPosition.make(Lit207, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1216733), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Maquinario.yail", 1216727);
        int[] iArr = new int[2];
        iArr[0] = -14336;
        Lit166 = IntNum.make(iArr);
        int[] iArr2 = new int[2];
        iArr2[0] = -16777216;
        Lit164 = IntNum.make(iArr2);
        int[] iArr3 = new int[2];
        iArr3[0] = -14336;
        Lit160 = IntNum.make(iArr3);
        int[] iArr4 = new int[2];
        iArr4[0] = -12303292;
        Lit156 = IntNum.make(iArr4);
        int[] iArr5 = new int[2];
        iArr5[0] = -12303292;
        Lit146 = IntNum.make(iArr5);
        int[] iArr6 = new int[2];
        iArr6[0] = -14336;
        Lit138 = IntNum.make(iArr6);
        int[] iArr7 = new int[2];
        iArr7[0] = -16777216;
        Lit136 = IntNum.make(iArr7);
        int[] iArr8 = new int[2];
        iArr8[0] = -14336;
        Lit132 = IntNum.make(iArr8);
        int[] iArr9 = new int[2];
        iArr9[0] = -12303292;
        Lit128 = IntNum.make(iArr9);
        int[] iArr10 = new int[2];
        iArr10[0] = -12303292;
        Lit118 = IntNum.make(iArr10);
        int[] iArr11 = new int[2];
        iArr11[0] = -14336;
        Lit110 = IntNum.make(iArr11);
        int[] iArr12 = new int[2];
        iArr12[0] = -16777216;
        Lit107 = IntNum.make(iArr12);
        int[] iArr13 = new int[2];
        iArr13[0] = -14336;
        Lit103 = IntNum.make(iArr13);
        int[] iArr14 = new int[2];
        iArr14[0] = -12303292;
        Lit96 = IntNum.make(iArr14);
        int[] iArr15 = new int[2];
        iArr15[0] = -12303292;
        Lit83 = IntNum.make(iArr15);
        int[] iArr16 = new int[2];
        iArr16[0] = -12303292;
        Lit55 = IntNum.make(iArr16);
        int[] iArr17 = new int[2];
        iArr17[0] = -12303292;
        Lit15 = IntNum.make(iArr17);
    }

    public Maquinario() {
        ModuleInfo.register(this);
        frame frame2 = new frame();
        frame2.$main = this;
        this.get$Mnsimple$Mnname = new ModuleMethod(frame2, 1, Lit193, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.onCreate = new ModuleMethod(frame2, 2, "onCreate", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.android$Mnlog$Mnform = new ModuleMethod(frame2, 3, Lit194, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnform$Mnenvironment = new ModuleMethod(frame2, 4, Lit195, 8194);
        this.lookup$Mnin$Mnform$Mnenvironment = new ModuleMethod(frame2, 5, Lit196, 8193);
        this.is$Mnbound$Mnin$Mnform$Mnenvironment = new ModuleMethod(frame2, 7, Lit197, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = new ModuleMethod(frame2, 8, Lit198, 8194);
        this.add$Mnto$Mnevents = new ModuleMethod(frame2, 9, Lit199, 8194);
        this.add$Mnto$Mncomponents = new ModuleMethod(frame2, 10, Lit200, 16388);
        this.add$Mnto$Mnglobal$Mnvars = new ModuleMethod(frame2, 11, Lit201, 8194);
        this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = new ModuleMethod(frame2, 12, Lit202, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.send$Mnerror = new ModuleMethod(frame2, 13, Lit203, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.process$Mnexception = new ModuleMethod(frame2, 14, "process-exception", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.dispatchEvent = new ModuleMethod(frame2, 15, Lit204, 16388);
        this.dispatchGenericEvent = new ModuleMethod(frame2, 16, Lit205, 16388);
        this.lookup$Mnhandler = new ModuleMethod(frame2, 17, Lit206, 8194);
        ModuleMethod moduleMethod = new ModuleMethod(frame2, 18, (Object) null, 0);
        moduleMethod.setProperty("source-location", "/tmp/runtime6229131719407907284.scm:627");
        lambda$Fn1 = moduleMethod;
        this.$define = new ModuleMethod(frame2, 19, "$define", 0);
        lambda$Fn2 = new ModuleMethod(frame2, 20, (Object) null, 0);
        lambda$Fn3 = new ModuleMethod(frame2, 21, (Object) null, 0);
        lambda$Fn4 = new ModuleMethod(frame2, 22, (Object) null, 0);
        lambda$Fn5 = new ModuleMethod(frame2, 23, (Object) null, 0);
        lambda$Fn6 = new ModuleMethod(frame2, 24, (Object) null, 0);
        this.Maquinario$Initialize = new ModuleMethod(frame2, 25, Lit43, 0);
        lambda$Fn7 = new ModuleMethod(frame2, 26, (Object) null, 0);
        lambda$Fn8 = new ModuleMethod(frame2, 27, (Object) null, 0);
        lambda$Fn9 = new ModuleMethod(frame2, 28, (Object) null, 0);
        lambda$Fn10 = new ModuleMethod(frame2, 29, (Object) null, 0);
        lambda$Fn11 = new ModuleMethod(frame2, 30, (Object) null, 0);
        lambda$Fn12 = new ModuleMethod(frame2, 31, (Object) null, 0);
        this.btn_voltarTela$Click = new ModuleMethod(frame2, 32, Lit68, 0);
        lambda$Fn13 = new ModuleMethod(frame2, 33, (Object) null, 0);
        lambda$Fn14 = new ModuleMethod(frame2, 34, (Object) null, 0);
        lambda$Fn15 = new ModuleMethod(frame2, 35, (Object) null, 0);
        lambda$Fn16 = new ModuleMethod(frame2, 36, (Object) null, 0);
        this.btn_cadastarMaquina$Click = new ModuleMethod(frame2, 37, Lit80, 0);
        lambda$Fn17 = new ModuleMethod(frame2, 38, (Object) null, 0);
        lambda$Fn18 = new ModuleMethod(frame2, 39, (Object) null, 0);
        lambda$Fn19 = new ModuleMethod(frame2, 40, (Object) null, 0);
        lambda$Fn20 = new ModuleMethod(frame2, 41, (Object) null, 0);
        lambda$Fn21 = new ModuleMethod(frame2, 42, (Object) null, 0);
        lambda$Fn22 = new ModuleMethod(frame2, 43, (Object) null, 0);
        lambda$Fn23 = new ModuleMethod(frame2, 44, (Object) null, 0);
        lambda$Fn24 = new ModuleMethod(frame2, 45, (Object) null, 0);
        lambda$Fn25 = new ModuleMethod(frame2, 46, (Object) null, 0);
        lambda$Fn26 = new ModuleMethod(frame2, 47, (Object) null, 0);
        this.btn_acessar1$Click = new ModuleMethod(frame2, 48, Lit115, 0);
        lambda$Fn27 = new ModuleMethod(frame2, 49, (Object) null, 0);
        lambda$Fn28 = new ModuleMethod(frame2, 50, (Object) null, 0);
        lambda$Fn29 = new ModuleMethod(frame2, 51, (Object) null, 0);
        lambda$Fn30 = new ModuleMethod(frame2, 52, (Object) null, 0);
        lambda$Fn31 = new ModuleMethod(frame2, 53, (Object) null, 0);
        lambda$Fn32 = new ModuleMethod(frame2, 54, (Object) null, 0);
        lambda$Fn33 = new ModuleMethod(frame2, 55, (Object) null, 0);
        lambda$Fn34 = new ModuleMethod(frame2, 56, (Object) null, 0);
        lambda$Fn35 = new ModuleMethod(frame2, 57, (Object) null, 0);
        lambda$Fn36 = new ModuleMethod(frame2, 58, (Object) null, 0);
        this.btn_acessar2$Click = new ModuleMethod(frame2, 59, Lit143, 0);
        lambda$Fn37 = new ModuleMethod(frame2, 60, (Object) null, 0);
        lambda$Fn38 = new ModuleMethod(frame2, 61, (Object) null, 0);
        lambda$Fn39 = new ModuleMethod(frame2, 62, (Object) null, 0);
        lambda$Fn40 = new ModuleMethod(frame2, 63, (Object) null, 0);
        lambda$Fn41 = new ModuleMethod(frame2, 64, (Object) null, 0);
        lambda$Fn42 = new ModuleMethod(frame2, 65, (Object) null, 0);
        lambda$Fn43 = new ModuleMethod(frame2, 66, (Object) null, 0);
        lambda$Fn44 = new ModuleMethod(frame2, 67, (Object) null, 0);
        lambda$Fn45 = new ModuleMethod(frame2, 68, (Object) null, 0);
        lambda$Fn46 = new ModuleMethod(frame2, 69, (Object) null, 0);
        this.btn_acessar3$Click = new ModuleMethod(frame2, 70, Lit171, 0);
        lambda$Fn47 = new ModuleMethod(frame2, 71, (Object) null, 0);
        lambda$Fn48 = new ModuleMethod(frame2, 72, (Object) null, 0);
        this.FirebaseDB1$GotValue = new ModuleMethod(frame2, 73, Lit191, 8194);
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
        Maquinario = null;
        this.form$Mnname$Mnsymbol = Lit0;
        this.events$Mnto$Mnregister = LList.Empty;
        this.components$Mnto$Mncreate = LList.Empty;
        this.global$Mnvars$Mnto$Mncreate = LList.Empty;
        this.form$Mndo$Mnafter$Mncreation = LList.Empty;
        C0718runtime.$instance.run();
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addGlobalVarToCurrentFormEnvironment(Lit3, C0718runtime.callYailPrimitive(C0718runtime.get$Mnstart$Mnvalue, LList.Empty, LList.Empty, "get start value")), $result);
        } else {
            addToGlobalVars(Lit3, lambda$Fn2);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addGlobalVarToCurrentFormEnvironment(Lit4, Lit5), $result);
        } else {
            addToGlobalVars(Lit4, lambda$Fn3);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addGlobalVarToCurrentFormEnvironment(Lit6, Lit7), $result);
        } else {
            addToGlobalVars(Lit6, lambda$Fn4);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addGlobalVarToCurrentFormEnvironment(Lit8, Lit9), $result);
        } else {
            addToGlobalVars(Lit8, lambda$Fn5);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit10, Boolean.TRUE, Lit11);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit12, "EVA_imobilizer", Lit13);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit14, Lit15, Lit16);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit17, "slidevertical", Lit13);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit18, "slidevertical", Lit13);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit19, "user", Lit13);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit20, Boolean.TRUE, Lit11);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit21, Boolean.TRUE, Lit11);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit22, "Responsive", Lit13);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit23, "AppTheme", Lit13);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit24, "Maquinários", Lit13);
            Values.writeValues(C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit25, Boolean.FALSE, Lit11), $result);
        } else {
            addToFormDoAfterCreation(new Promise(lambda$Fn6));
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit43, this.Maquinario$Initialize);
        } else {
            addToFormEnvironment(Lit43, this.Maquinario$Initialize);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "Maquinario", "Initialize");
        } else {
            addToEvents(Lit0, Lit44);
        }
        this.Legenda1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit45, Lit46, lambda$Fn7), $result);
        } else {
            addToComponents(Lit0, Lit51, Lit46, lambda$Fn8);
        }
        this.HorizontalScrollArrangement1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit52, Lit53, lambda$Fn9), $result);
        } else {
            addToComponents(Lit0, Lit58, Lit53, lambda$Fn10);
        }
        this.btn_voltarTela = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit53, Lit59, Lit60, lambda$Fn11), $result);
        } else {
            addToComponents(Lit53, Lit66, Lit60, lambda$Fn12);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit68, this.btn_voltarTela$Click);
        } else {
            addToFormEnvironment(Lit68, this.btn_voltarTela$Click);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "btn_voltarTela", "Click");
        } else {
            addToEvents(Lit60, Lit69);
        }
        this.HorizontalArrangement1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit53, Lit70, Lit71, lambda$Fn13), $result);
        } else {
            addToComponents(Lit53, Lit74, Lit71, lambda$Fn14);
        }
        this.btn_cadastarMaquina = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit53, Lit75, Lit76, lambda$Fn15), $result);
        } else {
            addToComponents(Lit53, Lit78, Lit76, lambda$Fn16);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit80, this.btn_cadastarMaquina$Click);
        } else {
            addToFormEnvironment(Lit80, this.btn_cadastarMaquina$Click);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "btn_cadastarMaquina", "Click");
        } else {
            addToEvents(Lit76, Lit69);
        }
        this.f412OrganizaoHorizontal1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit81, Lit82, lambda$Fn17), $result);
        } else {
            addToComponents(Lit0, Lit86, Lit82, lambda$Fn18);
        }
        this.img_escavadeira = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit82, Lit87, Lit88, lambda$Fn19), $result);
        } else {
            addToComponents(Lit82, Lit92, Lit88, lambda$Fn20);
        }
        this.f415OrganizaoVertical1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit82, Lit93, Lit94, lambda$Fn21), $result);
        } else {
            addToComponents(Lit82, Lit97, Lit94, lambda$Fn22);
        }
        this.txt_tituloMaquina = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit94, Lit98, Lit99, lambda$Fn23), $result);
        } else {
            addToComponents(Lit94, Lit104, Lit99, lambda$Fn24);
        }
        this.btn_acessar1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit94, Lit105, Lit106, lambda$Fn25), $result);
        } else {
            addToComponents(Lit94, Lit112, Lit106, lambda$Fn26);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit115, this.btn_acessar1$Click);
        } else {
            addToFormEnvironment(Lit115, this.btn_acessar1$Click);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "btn_acessar1", "Click");
        } else {
            addToEvents(Lit106, Lit69);
        }
        this.f413OrganizaoHorizontal2 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit116, Lit117, lambda$Fn27), $result);
        } else {
            addToComponents(Lit0, Lit120, Lit117, lambda$Fn28);
        }
        this.img_escavadeira2 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit117, Lit121, Lit122, lambda$Fn29), $result);
        } else {
            addToComponents(Lit117, Lit125, Lit122, lambda$Fn30);
        }
        this.f416OrganizaoVertical2 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit117, Lit126, Lit127, lambda$Fn31), $result);
        } else {
            addToComponents(Lit117, Lit129, Lit127, lambda$Fn32);
        }
        this.txt_tituloMaquina2 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit127, Lit130, Lit131, lambda$Fn33), $result);
        } else {
            addToComponents(Lit127, Lit133, Lit131, lambda$Fn34);
        }
        this.btn_acessar2 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit127, Lit134, Lit135, lambda$Fn35), $result);
        } else {
            addToComponents(Lit127, Lit140, Lit135, lambda$Fn36);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit143, this.btn_acessar2$Click);
        } else {
            addToFormEnvironment(Lit143, this.btn_acessar2$Click);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "btn_acessar2", "Click");
        } else {
            addToEvents(Lit135, Lit69);
        }
        this.f414OrganizaoHorizontal3 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit144, Lit145, lambda$Fn37), $result);
        } else {
            addToComponents(Lit0, Lit148, Lit145, lambda$Fn38);
        }
        this.img_escavadeira3 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit145, Lit149, Lit150, lambda$Fn39), $result);
        } else {
            addToComponents(Lit145, Lit153, Lit150, lambda$Fn40);
        }
        this.f417OrganizaoVertical3 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit145, Lit154, Lit155, lambda$Fn41), $result);
        } else {
            addToComponents(Lit145, Lit157, Lit155, lambda$Fn42);
        }
        this.txt_tituloMaquina3 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit155, Lit158, Lit159, lambda$Fn43), $result);
        } else {
            addToComponents(Lit155, Lit161, Lit159, lambda$Fn44);
        }
        this.btn_acessar3 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit155, Lit162, Lit163, lambda$Fn45), $result);
        } else {
            addToComponents(Lit155, Lit168, Lit163, lambda$Fn46);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit171, this.btn_acessar3$Click);
        } else {
            addToFormEnvironment(Lit171, this.btn_acessar3$Click);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "btn_acessar3", "Click");
        } else {
            addToEvents(Lit163, Lit69);
        }
        this.FirebaseDB1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit172, Lit26, lambda$Fn47), $result);
        } else {
            addToComponents(Lit0, Lit178, Lit26, lambda$Fn48);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit191, this.FirebaseDB1$GotValue);
        } else {
            addToFormEnvironment(Lit191, this.FirebaseDB1$GotValue);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "FirebaseDB1", "GotValue");
        } else {
            addToEvents(Lit26, Lit192);
        }
        C0718runtime.initRuntime();
    }

    static Object lambda3() {
        return C0718runtime.callYailPrimitive(C0718runtime.get$Mnstart$Mnvalue, LList.Empty, LList.Empty, "get start value");
    }

    static IntNum lambda4() {
        return Lit5;
    }

    static IntNum lambda5() {
        return Lit7;
    }

    static IntNum lambda6() {
        return Lit9;
    }

    static Object lambda7() {
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit10, Boolean.TRUE, Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit12, "EVA_imobilizer", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit14, Lit15, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit17, "slidevertical", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit18, "slidevertical", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit19, "user", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit20, Boolean.TRUE, Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit21, Boolean.TRUE, Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit22, "Responsive", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit23, "AppTheme", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit24, "Maquinários", Lit13);
        return C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit25, Boolean.FALSE, Lit11);
    }

    public Object Maquinario$Initialize() {
        C0718runtime.setThisForm();
        SimpleSymbol simpleSymbol = Lit26;
        SimpleSymbol simpleSymbol2 = Lit27;
        Object callYailPrimitive = C0718runtime.callYailPrimitive(C0718runtime.string$Mnreplace$Mnall, LList.list3(C0718runtime.lookupGlobalVarInCurrentFormEnvironment(Lit3, C0718runtime.$Stthe$Mnnull$Mnvalue$St), ".", "_"), Lit28, "replace all");
        ModuleMethod moduleMethod = C0718runtime.make$Mnyail$Mnlist;
        Pair list1 = LList.list1(C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit5, Lit29), Lit30, "random integer"));
        LList.chain1(LList.chain1(LList.chain4(LList.chain4(list1, C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit5, Lit29), Lit31, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit5, Lit29), Lit32, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit5, Lit29), Lit33, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit5, Lit29), Lit34, "random integer")), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit5, Lit29), Lit35, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit5, Lit29), Lit36, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit5, Lit29), Lit37, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit5, Lit29), Lit38, "random integer")), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit5, Lit29), Lit39, "random integer")), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit5, Lit29), Lit40, "random integer"));
        return C0718runtime.callComponentMethod(simpleSymbol, simpleSymbol2, LList.list2(callYailPrimitive, C0718runtime.callYailPrimitive(moduleMethod, list1, Lit41, "make a list")), Lit42);
    }

    static Object lambda8() {
        C0718runtime.setAndCoerceProperty$Ex(Lit46, Lit47, Lit48, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit46, Lit49, Lit50, Lit16);
    }

    static Object lambda9() {
        C0718runtime.setAndCoerceProperty$Ex(Lit46, Lit47, Lit48, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit46, Lit49, Lit50, Lit16);
    }

    static Object lambda10() {
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit54, Lit7, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit14, Lit55, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit56, Lit57, Lit16);
    }

    static Object lambda11() {
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit54, Lit7, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit14, Lit55, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit56, Lit57, Lit16);
    }

    static Object lambda12() {
        C0718runtime.setAndCoerceProperty$Ex(Lit60, Lit14, Lit61, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit60, Lit47, Lit62, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit60, Lit63, "setaCorreta-removebg-preview.png", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit60, Lit49, Lit64, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit60, Lit56, Lit65, Lit16);
    }

    static Object lambda13() {
        C0718runtime.setAndCoerceProperty$Ex(Lit60, Lit14, Lit61, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit60, Lit47, Lit62, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit60, Lit63, "setaCorreta-removebg-preview.png", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit60, Lit49, Lit64, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit60, Lit56, Lit65, Lit16);
    }

    public Object btn_voltarTela$Click() {
        C0718runtime.setThisForm();
        return C0718runtime.callYailPrimitive(C0718runtime.open$Mnanother$Mnscreen, LList.list1("Screen1"), Lit67, "open another screen");
    }

    static Object lambda14() {
        C0718runtime.setAndCoerceProperty$Ex(Lit71, Lit47, Lit72, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit71, Lit56, Lit73, Lit16);
    }

    static Object lambda15() {
        C0718runtime.setAndCoerceProperty$Ex(Lit71, Lit47, Lit72, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit71, Lit56, Lit73, Lit16);
    }

    static Object lambda16() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit76, Lit77, "Cadastrar Maquina", Lit13);
    }

    static Object lambda17() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit76, Lit77, "Cadastrar Maquina", Lit13);
    }

    public Object btn_cadastarMaquina$Click() {
        C0718runtime.setThisForm();
        return C0718runtime.callYailPrimitive(C0718runtime.open$Mnanother$Mnscreen, LList.list1("CadastroMaquinas"), Lit79, "open another screen");
    }

    static Object lambda18() {
        C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit54, Lit7, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit14, Lit83, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit47, Lit84, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit85, Boolean.FALSE, Lit11);
        return C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit56, Lit57, Lit16);
    }

    static Object lambda19() {
        C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit54, Lit7, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit14, Lit83, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit47, Lit84, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit85, Boolean.FALSE, Lit11);
        return C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit56, Lit57, Lit16);
    }

    static Object lambda20() {
        C0718runtime.setAndCoerceProperty$Ex(Lit88, Lit47, Lit89, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit88, Lit90, "jcb_escavadeira.jpeg", Lit13);
        return C0718runtime.setAndCoerceProperty$Ex(Lit88, Lit56, Lit91, Lit16);
    }

    static Object lambda21() {
        C0718runtime.setAndCoerceProperty$Ex(Lit88, Lit47, Lit89, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit88, Lit90, "jcb_escavadeira.jpeg", Lit13);
        return C0718runtime.setAndCoerceProperty$Ex(Lit88, Lit56, Lit91, Lit16);
    }

    static Object lambda22() {
        C0718runtime.setAndCoerceProperty$Ex(Lit94, Lit95, Lit9, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit94, Lit54, Lit7, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit94, Lit14, Lit96, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit94, Lit56, Lit57, Lit16);
    }

    static Object lambda23() {
        C0718runtime.setAndCoerceProperty$Ex(Lit94, Lit95, Lit9, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit94, Lit54, Lit7, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit94, Lit14, Lit96, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit94, Lit56, Lit57, Lit16);
    }

    static Object lambda24() {
        C0718runtime.setAndCoerceProperty$Ex(Lit99, Lit100, Lit101, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit99, Lit77, "Escavadeira Hidráulica 1", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit99, Lit102, Lit5, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit99, Lit49, Lit103, Lit16);
    }

    static Object lambda25() {
        C0718runtime.setAndCoerceProperty$Ex(Lit99, Lit100, Lit101, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit99, Lit77, "Escavadeira Hidráulica 1", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit99, Lit102, Lit5, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit99, Lit49, Lit103, Lit16);
    }

    static Object lambda26() {
        C0718runtime.setAndCoerceProperty$Ex(Lit106, Lit14, Lit107, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit106, Lit108, Boolean.TRUE, Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit106, Lit47, Lit109, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit106, Lit77, "conectar", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit106, Lit49, Lit110, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit106, Lit56, Lit111, Lit16);
    }

    static Object lambda27() {
        C0718runtime.setAndCoerceProperty$Ex(Lit106, Lit14, Lit107, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit106, Lit108, Boolean.TRUE, Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit106, Lit47, Lit109, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit106, Lit77, "conectar", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit106, Lit49, Lit110, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit106, Lit56, Lit111, Lit16);
    }

    public Object btn_acessar1$Click() {
        C0718runtime.setThisForm();
        return C0718runtime.callYailPrimitive(C0718runtime.open$Mnanother$Mnscreen$Mnwith$Mnstart$Mnvalue, LList.list2("Comandos", C0718runtime.callYailPrimitive(C0718runtime.make$Mnyail$Mnlist, LList.list2(C0718runtime.lookupGlobalVarInCurrentFormEnvironment(Lit3, C0718runtime.$Stthe$Mnnull$Mnvalue$St), C0718runtime.lookupGlobalVarInCurrentFormEnvironment(Lit4, C0718runtime.$Stthe$Mnnull$Mnvalue$St)), Lit113, "make a list")), Lit114, "open another screen with start value");
    }

    static Object lambda28() {
        C0718runtime.setAndCoerceProperty$Ex(Lit117, Lit54, Lit7, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit117, Lit14, Lit118, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit117, Lit47, Lit119, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit117, Lit85, Boolean.FALSE, Lit11);
        return C0718runtime.setAndCoerceProperty$Ex(Lit117, Lit56, Lit57, Lit16);
    }

    static Object lambda29() {
        C0718runtime.setAndCoerceProperty$Ex(Lit117, Lit54, Lit7, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit117, Lit14, Lit118, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit117, Lit47, Lit119, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit117, Lit85, Boolean.FALSE, Lit11);
        return C0718runtime.setAndCoerceProperty$Ex(Lit117, Lit56, Lit57, Lit16);
    }

    static Object lambda30() {
        C0718runtime.setAndCoerceProperty$Ex(Lit122, Lit47, Lit123, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit122, Lit90, "jcb_escavadeira.jpeg", Lit13);
        return C0718runtime.setAndCoerceProperty$Ex(Lit122, Lit56, Lit124, Lit16);
    }

    static Object lambda31() {
        C0718runtime.setAndCoerceProperty$Ex(Lit122, Lit47, Lit123, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit122, Lit90, "jcb_escavadeira.jpeg", Lit13);
        return C0718runtime.setAndCoerceProperty$Ex(Lit122, Lit56, Lit124, Lit16);
    }

    static Object lambda32() {
        C0718runtime.setAndCoerceProperty$Ex(Lit127, Lit95, Lit9, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit127, Lit54, Lit7, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit127, Lit14, Lit128, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit127, Lit56, Lit57, Lit16);
    }

    static Object lambda33() {
        C0718runtime.setAndCoerceProperty$Ex(Lit127, Lit95, Lit9, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit127, Lit54, Lit7, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit127, Lit14, Lit128, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit127, Lit56, Lit57, Lit16);
    }

    static Object lambda34() {
        C0718runtime.setAndCoerceProperty$Ex(Lit131, Lit100, Lit101, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit131, Lit77, "Escavadeira Hidráulica 1", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit131, Lit102, Lit5, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit131, Lit49, Lit132, Lit16);
    }

    static Object lambda35() {
        C0718runtime.setAndCoerceProperty$Ex(Lit131, Lit100, Lit101, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit131, Lit77, "Escavadeira Hidráulica 1", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit131, Lit102, Lit5, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit131, Lit49, Lit132, Lit16);
    }

    static Object lambda36() {
        C0718runtime.setAndCoerceProperty$Ex(Lit135, Lit14, Lit136, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit135, Lit108, Boolean.TRUE, Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit135, Lit47, Lit137, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit135, Lit77, "conectar", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit135, Lit49, Lit138, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit135, Lit56, Lit139, Lit16);
    }

    static Object lambda37() {
        C0718runtime.setAndCoerceProperty$Ex(Lit135, Lit14, Lit136, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit135, Lit108, Boolean.TRUE, Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit135, Lit47, Lit137, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit135, Lit77, "conectar", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit135, Lit49, Lit138, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit135, Lit56, Lit139, Lit16);
    }

    public Object btn_acessar2$Click() {
        C0718runtime.setThisForm();
        return C0718runtime.callYailPrimitive(C0718runtime.open$Mnanother$Mnscreen$Mnwith$Mnstart$Mnvalue, LList.list2("Comandos", C0718runtime.callYailPrimitive(C0718runtime.make$Mnyail$Mnlist, LList.list2(C0718runtime.lookupGlobalVarInCurrentFormEnvironment(Lit3, C0718runtime.$Stthe$Mnnull$Mnvalue$St), C0718runtime.lookupGlobalVarInCurrentFormEnvironment(Lit6, C0718runtime.$Stthe$Mnnull$Mnvalue$St)), Lit141, "make a list")), Lit142, "open another screen with start value");
    }

    static Object lambda38() {
        C0718runtime.setAndCoerceProperty$Ex(Lit145, Lit54, Lit7, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit145, Lit14, Lit146, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit145, Lit47, Lit147, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit145, Lit85, Boolean.FALSE, Lit11);
        return C0718runtime.setAndCoerceProperty$Ex(Lit145, Lit56, Lit57, Lit16);
    }

    static Object lambda39() {
        C0718runtime.setAndCoerceProperty$Ex(Lit145, Lit54, Lit7, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit145, Lit14, Lit146, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit145, Lit47, Lit147, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit145, Lit85, Boolean.FALSE, Lit11);
        return C0718runtime.setAndCoerceProperty$Ex(Lit145, Lit56, Lit57, Lit16);
    }

    static Object lambda40() {
        C0718runtime.setAndCoerceProperty$Ex(Lit150, Lit47, Lit151, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit150, Lit90, "jcb_escavadeira.jpeg", Lit13);
        return C0718runtime.setAndCoerceProperty$Ex(Lit150, Lit56, Lit152, Lit16);
    }

    static Object lambda41() {
        C0718runtime.setAndCoerceProperty$Ex(Lit150, Lit47, Lit151, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit150, Lit90, "jcb_escavadeira.jpeg", Lit13);
        return C0718runtime.setAndCoerceProperty$Ex(Lit150, Lit56, Lit152, Lit16);
    }

    static Object lambda42() {
        C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit95, Lit9, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit54, Lit7, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit14, Lit156, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit56, Lit57, Lit16);
    }

    static Object lambda43() {
        C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit95, Lit9, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit54, Lit7, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit14, Lit156, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit56, Lit57, Lit16);
    }

    static Object lambda44() {
        C0718runtime.setAndCoerceProperty$Ex(Lit159, Lit100, Lit101, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit159, Lit77, "Escavadeira Hidráulica 1", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit159, Lit102, Lit5, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit159, Lit49, Lit160, Lit16);
    }

    static Object lambda45() {
        C0718runtime.setAndCoerceProperty$Ex(Lit159, Lit100, Lit101, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit159, Lit77, "Escavadeira Hidráulica 1", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit159, Lit102, Lit5, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit159, Lit49, Lit160, Lit16);
    }

    static Object lambda46() {
        C0718runtime.setAndCoerceProperty$Ex(Lit163, Lit14, Lit164, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit163, Lit108, Boolean.TRUE, Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit163, Lit47, Lit165, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit163, Lit77, "conectar", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit163, Lit49, Lit166, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit163, Lit56, Lit167, Lit16);
    }

    static Object lambda47() {
        C0718runtime.setAndCoerceProperty$Ex(Lit163, Lit14, Lit164, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit163, Lit108, Boolean.TRUE, Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit163, Lit47, Lit165, Lit16);
        C0718runtime.setAndCoerceProperty$Ex(Lit163, Lit77, "conectar", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit163, Lit49, Lit166, Lit16);
        return C0718runtime.setAndCoerceProperty$Ex(Lit163, Lit56, Lit167, Lit16);
    }

    public Object btn_acessar3$Click() {
        C0718runtime.setThisForm();
        return C0718runtime.callYailPrimitive(C0718runtime.open$Mnanother$Mnscreen$Mnwith$Mnstart$Mnvalue, LList.list2("Comandos", C0718runtime.callYailPrimitive(C0718runtime.make$Mnyail$Mnlist, LList.list2(C0718runtime.lookupGlobalVarInCurrentFormEnvironment(Lit3, C0718runtime.$Stthe$Mnnull$Mnvalue$St), C0718runtime.lookupGlobalVarInCurrentFormEnvironment(Lit8, C0718runtime.$Stthe$Mnnull$Mnvalue$St)), Lit169, "make a list")), Lit170, "open another screen with start value");
    }

    static Object lambda48() {
        C0718runtime.setAndCoerceProperty$Ex(Lit26, Lit173, "https://dazzling-fire-7140.firebaseio.com/", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit26, Lit174, "eva:tcctelemetria@gmail:com/", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit26, Lit175, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkIjp7InVpZCI6ImZiYWYwNmY5LTVkZGItNDNiYi04MDA2LTU3YjY4NjMyMWJkOCIsInByb2plY3QiOiJFVkFfaW1vYmlsaXplciIsImRldmVsb3BlciI6ImV2YTp0Y2N0ZWxlbWV0cmlhQGdtYWlsOmNvbSJ9LCJ2IjowLCJleHAiOjE2NzUxNDYwNjIyLCJpYXQiOjE2NjcyMzAyMjJ9.FN4FSJWnEGPiF5xgXItnK6WYAxv76s9u1rfSydv7rLE", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit26, Lit176, "https://unihawk-immobilizer-v1-default-rtdb.firebaseio.com/", Lit13);
        return C0718runtime.setAndCoerceProperty$Ex(Lit26, Lit177, "EVA_imobilizer", Lit13);
    }

    static Object lambda49() {
        C0718runtime.setAndCoerceProperty$Ex(Lit26, Lit173, "https://dazzling-fire-7140.firebaseio.com/", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit26, Lit174, "eva:tcctelemetria@gmail:com/", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit26, Lit175, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkIjp7InVpZCI6ImZiYWYwNmY5LTVkZGItNDNiYi04MDA2LTU3YjY4NjMyMWJkOCIsInByb2plY3QiOiJFVkFfaW1vYmlsaXplciIsImRldmVsb3BlciI6ImV2YTp0Y2N0ZWxlbWV0cmlhQGdtYWlsOmNvbSJ9LCJ2IjowLCJleHAiOjE2NzUxNDYwNjIyLCJpYXQiOjE2NjcyMzAyMjJ9.FN4FSJWnEGPiF5xgXItnK6WYAxv76s9u1rfSydv7rLE", Lit13);
        C0718runtime.setAndCoerceProperty$Ex(Lit26, Lit176, "https://unihawk-immobilizer-v1-default-rtdb.firebaseio.com/", Lit13);
        return C0718runtime.setAndCoerceProperty$Ex(Lit26, Lit177, "EVA_imobilizer", Lit13);
    }

    /* compiled from: Maquinario.yail */
    public class frame extends ModuleBody {
        Maquinario $main;

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 1:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 2:
                    if (!(obj instanceof Maquinario)) {
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
                    if (!(obj instanceof Maquinario)) {
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
                case 73:
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
                    if (!(obj instanceof Maquinario)) {
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
                    if (!(obj instanceof Maquinario)) {
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
                    Maquinario maquinario = this.$main;
                    try {
                        Component component = (Component) obj;
                        try {
                            String str = (String) obj2;
                            try {
                                if (obj3 == Boolean.FALSE) {
                                    z = false;
                                }
                                try {
                                    maquinario.dispatchGenericEvent(component, str, z, (Object[]) obj4);
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
                case 73:
                    return this.$main.FirebaseDB1$GotValue(obj, obj2);
                default:
                    return super.apply2(moduleMethod, obj, obj2);
            }
        }

        public Object apply0(ModuleMethod moduleMethod) {
            switch (moduleMethod.selector) {
                case 18:
                    return Maquinario.lambda2();
                case 19:
                    this.$main.$define();
                    return Values.empty;
                case 20:
                    return Maquinario.lambda3();
                case 21:
                    return Maquinario.lambda4();
                case 22:
                    return Maquinario.lambda5();
                case 23:
                    return Maquinario.lambda6();
                case 24:
                    return Maquinario.lambda7();
                case 25:
                    return this.$main.Maquinario$Initialize();
                case 26:
                    return Maquinario.lambda8();
                case 27:
                    return Maquinario.lambda9();
                case 28:
                    return Maquinario.lambda10();
                case 29:
                    return Maquinario.lambda11();
                case 30:
                    return Maquinario.lambda12();
                case 31:
                    return Maquinario.lambda13();
                case 32:
                    return this.$main.btn_voltarTela$Click();
                case 33:
                    return Maquinario.lambda14();
                case 34:
                    return Maquinario.lambda15();
                case 35:
                    return Maquinario.lambda16();
                case 36:
                    return Maquinario.lambda17();
                case 37:
                    return this.$main.btn_cadastarMaquina$Click();
                case 38:
                    return Maquinario.lambda18();
                case 39:
                    return Maquinario.lambda19();
                case 40:
                    return Maquinario.lambda20();
                case 41:
                    return Maquinario.lambda21();
                case 42:
                    return Maquinario.lambda22();
                case 43:
                    return Maquinario.lambda23();
                case 44:
                    return Maquinario.lambda24();
                case 45:
                    return Maquinario.lambda25();
                case 46:
                    return Maquinario.lambda26();
                case 47:
                    return Maquinario.lambda27();
                case 48:
                    return this.$main.btn_acessar1$Click();
                case 49:
                    return Maquinario.lambda28();
                case 50:
                    return Maquinario.lambda29();
                case 51:
                    return Maquinario.lambda30();
                case 52:
                    return Maquinario.lambda31();
                case 53:
                    return Maquinario.lambda32();
                case 54:
                    return Maquinario.lambda33();
                case 55:
                    return Maquinario.lambda34();
                case 56:
                    return Maquinario.lambda35();
                case 57:
                    return Maquinario.lambda36();
                case 58:
                    return Maquinario.lambda37();
                case 59:
                    return this.$main.btn_acessar2$Click();
                case 60:
                    return Maquinario.lambda38();
                case 61:
                    return Maquinario.lambda39();
                case 62:
                    return Maquinario.lambda40();
                case 63:
                    return Maquinario.lambda41();
                case 64:
                    return Maquinario.lambda42();
                case 65:
                    return Maquinario.lambda43();
                case 66:
                    return Maquinario.lambda44();
                case 67:
                    return Maquinario.lambda45();
                case 68:
                    return Maquinario.lambda46();
                case 69:
                    return Maquinario.lambda47();
                case 70:
                    return this.$main.btn_acessar3$Click();
                case 71:
                    return Maquinario.lambda48();
                case 72:
                    return Maquinario.lambda49();
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
                case 65:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 66:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 67:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 68:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 69:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 70:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 71:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 72:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                default:
                    return super.match0(moduleMethod, callContext);
            }
        }
    }

    public Object FirebaseDB1$GotValue(Object $tag, Object $value) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        C0718runtime.sanitizeComponentData($tag);
        Object $value2 = C0718runtime.sanitizeComponentData($value);
        C0718runtime.setThisForm();
        ModuleMethod moduleMethod = C0718runtime.yail$Mnnot$Mnequal$Qu;
        ModuleMethod moduleMethod2 = C0718runtime.yail$Mnlist$Mnget$Mnitem;
        if ($value2 instanceof Package) {
            obj = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Lit179), " is not bound in the current context"), "Unbound Variable");
        } else {
            obj = $value2;
        }
        if (C0718runtime.callYailPrimitive(moduleMethod, LList.list2(C0718runtime.callYailPrimitive(moduleMethod2, LList.list2(obj, Lit180), Lit181, "select list item"), ""), Lit182, "=") != Boolean.FALSE) {
            C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit85, Boolean.TRUE, Lit11);
            SimpleSymbol simpleSymbol = Lit99;
            SimpleSymbol simpleSymbol2 = Lit77;
            ModuleMethod moduleMethod3 = C0718runtime.yail$Mnlist$Mnget$Mnitem;
            if ($value2 instanceof Package) {
                obj5 = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Lit179), " is not bound in the current context"), "Unbound Variable");
            } else {
                obj5 = $value2;
            }
            C0718runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, C0718runtime.callYailPrimitive(moduleMethod3, LList.list2(obj5, Lit180), Lit183, "select list item"), Lit13);
        }
        ModuleMethod moduleMethod4 = C0718runtime.yail$Mnnot$Mnequal$Qu;
        ModuleMethod moduleMethod5 = C0718runtime.yail$Mnlist$Mnget$Mnitem;
        if ($value2 instanceof Package) {
            obj2 = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Lit179), " is not bound in the current context"), "Unbound Variable");
        } else {
            obj2 = $value2;
        }
        if (C0718runtime.callYailPrimitive(moduleMethod4, LList.list2(C0718runtime.callYailPrimitive(moduleMethod5, LList.list2(obj2, Lit184), Lit185, "select list item"), ""), Lit186, "=") != Boolean.FALSE) {
            C0718runtime.setAndCoerceProperty$Ex(Lit117, Lit85, Boolean.TRUE, Lit11);
            SimpleSymbol simpleSymbol3 = Lit131;
            SimpleSymbol simpleSymbol4 = Lit77;
            ModuleMethod moduleMethod6 = C0718runtime.yail$Mnlist$Mnget$Mnitem;
            if ($value2 instanceof Package) {
                obj4 = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Lit179), " is not bound in the current context"), "Unbound Variable");
            } else {
                obj4 = $value2;
            }
            C0718runtime.setAndCoerceProperty$Ex(simpleSymbol3, simpleSymbol4, C0718runtime.callYailPrimitive(moduleMethod6, LList.list2(obj4, Lit184), Lit187, "select list item"), Lit13);
        }
        ModuleMethod moduleMethod7 = C0718runtime.yail$Mnnot$Mnequal$Qu;
        ModuleMethod moduleMethod8 = C0718runtime.yail$Mnlist$Mnget$Mnitem;
        if ($value2 instanceof Package) {
            obj3 = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Lit179), " is not bound in the current context"), "Unbound Variable");
        } else {
            obj3 = $value2;
        }
        if (C0718runtime.callYailPrimitive(moduleMethod7, LList.list2(C0718runtime.callYailPrimitive(moduleMethod8, LList.list2(obj3, Lit72), Lit188, "select list item"), ""), Lit189, "=") == Boolean.FALSE) {
            return Values.empty;
        }
        C0718runtime.setAndCoerceProperty$Ex(Lit145, Lit85, Boolean.TRUE, Lit11);
        SimpleSymbol simpleSymbol5 = Lit159;
        SimpleSymbol simpleSymbol6 = Lit77;
        ModuleMethod moduleMethod9 = C0718runtime.yail$Mnlist$Mnget$Mnitem;
        if ($value2 instanceof Package) {
            $value2 = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Lit179), " is not bound in the current context"), "Unbound Variable");
        }
        return C0718runtime.setAndCoerceProperty$Ex(simpleSymbol5, simpleSymbol6, C0718runtime.callYailPrimitive(moduleMethod9, LList.list2($value2, Lit72), Lit190, "select list item"), Lit13);
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
        Maquinario = this;
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
