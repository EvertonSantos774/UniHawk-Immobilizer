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
import com.google.appinventor.components.runtime.Texting;
import com.google.appinventor.components.runtime.VerticalScrollArrangement;
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

/* compiled from: Comandos.yail */
public class Comandos extends Form implements Runnable {
    public static Comandos Comandos;
    static final SimpleSymbol Lit0 = ((SimpleSymbol) new SimpleSymbol("Comandos").readResolve());
    static final SimpleSymbol Lit1 = ((SimpleSymbol) new SimpleSymbol("getMessage").readResolve());
    static final SimpleSymbol Lit10 = ((SimpleSymbol) new SimpleSymbol("BackgroundColor").readResolve());
    static final SimpleSymbol Lit100 = ((SimpleSymbol) new SimpleSymbol("btn_bloquear_ignicao$Click").readResolve());
    static final FString Lit101 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit102 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal4").readResolve());
    static final IntNum Lit103;
    static final IntNum Lit104 = IntNum.make(-1020);
    static final FString Lit105 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit106 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit107 = ((SimpleSymbol) new SimpleSymbol("btn_desbloquear_ignicao").readResolve());
    static final IntNum Lit108 = IntNum.make(-1022);
    static final IntNum Lit109 = IntNum.make(16777215);
    static final IntNum Lit11;
    static final IntNum Lit110 = IntNum.make(-1035);
    static final FString Lit111 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit112 = ((SimpleSymbol) new SimpleSymbol("btn_desbloquear_ignicao$Click").readResolve());
    static final FString Lit113 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit114 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal7").readResolve());
    static final IntNum Lit115;
    static final FString Lit116 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit117 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit118 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal9").readResolve());
    static final IntNum Lit119;
    static final SimpleSymbol Lit12;
    static final IntNum Lit120 = IntNum.make(-1014);
    static final FString Lit121 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit122 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit123 = ((SimpleSymbol) new SimpleSymbol("Legenda1").readResolve());
    static final IntNum Lit124;
    static final FString Lit125 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit126 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit127 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal8").readResolve());
    static final IntNum Lit128;
    static final IntNum Lit129 = IntNum.make(-1035);
    static final SimpleSymbol Lit13 = ((SimpleSymbol) new SimpleSymbol("CloseScreenAnimation").readResolve());
    static final FString Lit130 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit131 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit132 = ((SimpleSymbol) new SimpleSymbol("Legenda2").readResolve());
    static final IntNum Lit133;
    static final IntNum Lit134;
    static final FString Lit135 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit136 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit137 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal28").readResolve());
    static final IntNum Lit138;
    static final FString Lit139 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit14 = ((SimpleSymbol) new SimpleSymbol("OpenScreenAnimation").readResolve());
    static final FString Lit140 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit141 = ((SimpleSymbol) new SimpleSymbol("txt_comandoCombustivel").readResolve());
    static final IntNum Lit142;
    static final FString Lit143 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit144 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit145 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal3").readResolve());
    static final IntNum Lit146;
    static final IntNum Lit147 = IntNum.make(-1023);
    static final FString Lit148 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit149 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit15 = ((SimpleSymbol) new SimpleSymbol("ScreenOrientation").readResolve());
    static final SimpleSymbol Lit150 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal26").readResolve());
    static final IntNum Lit151;
    static final IntNum Lit152 = IntNum.make(-1005);
    static final FString Lit153 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit154 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit155 = ((SimpleSymbol) new SimpleSymbol("btn_bloquear_bateria1").readResolve());
    static final IntNum Lit156;
    static final IntNum Lit157 = IntNum.make(-1022);
    static final IntNum Lit158 = IntNum.make(16777215);
    static final IntNum Lit159 = IntNum.make(-1035);
    static final SimpleSymbol Lit16 = ((SimpleSymbol) new SimpleSymbol("Scrollable").readResolve());
    static final FString Lit160 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit161 = ((SimpleSymbol) new SimpleSymbol("btn_desbloquear_bateria1").readResolve());
    static final PairWithPosition Lit162 = PairWithPosition.make(Lit315, PairWithPosition.make(Lit315, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 1130621), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 1130616);
    static final SimpleSymbol Lit163 = ((SimpleSymbol) new SimpleSymbol("Notificador1").readResolve());
    static final SimpleSymbol Lit164 = ((SimpleSymbol) new SimpleSymbol("ShowAlert").readResolve());
    static final PairWithPosition Lit165 = PairWithPosition.make(Lit9, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 1131123);
    static final SimpleSymbol Lit166 = ((SimpleSymbol) new SimpleSymbol("btn_bloquear_bateria1$Click").readResolve());
    static final FString Lit167 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit168 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal6").readResolve());
    static final IntNum Lit169;
    static final SimpleSymbol Lit17 = ((SimpleSymbol) new SimpleSymbol("ShowListsAsJson").readResolve());
    static final IntNum Lit170 = IntNum.make(-1020);
    static final FString Lit171 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit172 = new FString("com.google.appinventor.components.runtime.Button");
    static final IntNum Lit173;
    static final IntNum Lit174 = IntNum.make(-1022);
    static final IntNum Lit175 = IntNum.make(16777215);
    static final IntNum Lit176 = IntNum.make(-1035);
    static final FString Lit177 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit178 = PairWithPosition.make(Lit315, PairWithPosition.make(Lit315, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 1245306), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 1245301);
    static final PairWithPosition Lit179 = PairWithPosition.make(Lit9, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 1245806);
    static final SimpleSymbol Lit18 = ((SimpleSymbol) new SimpleSymbol("ShowStatusBar").readResolve());
    static final SimpleSymbol Lit180 = ((SimpleSymbol) new SimpleSymbol("btn_desbloquear_bateria1$Click").readResolve());
    static final FString Lit181 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit182 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal13").readResolve());
    static final IntNum Lit183;
    static final FString Lit184 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit185 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit186 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal14").readResolve());
    static final IntNum Lit187;
    static final IntNum Lit188 = IntNum.make(-1014);
    static final FString Lit189 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit19 = ((SimpleSymbol) new SimpleSymbol("Sizing").readResolve());
    static final FString Lit190 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit191 = ((SimpleSymbol) new SimpleSymbol("Legenda5").readResolve());
    static final IntNum Lit192;
    static final IntNum Lit193;
    static final FString Lit194 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit195 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit196 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal15").readResolve());
    static final IntNum Lit197;
    static final IntNum Lit198 = IntNum.make(-1035);
    static final FString Lit199 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit2 = ((SimpleSymbol) new SimpleSymbol("*the-null-value*").readResolve());
    static final SimpleSymbol Lit20 = ((SimpleSymbol) new SimpleSymbol("Theme").readResolve());
    static final FString Lit200 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit201 = ((SimpleSymbol) new SimpleSymbol("Legenda6").readResolve());
    static final IntNum Lit202;
    static final IntNum Lit203;
    static final FString Lit204 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit205 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit206 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal29").readResolve());
    static final IntNum Lit207;
    static final FString Lit208 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit209 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit21 = ((SimpleSymbol) new SimpleSymbol("Title").readResolve());
    static final SimpleSymbol Lit210 = ((SimpleSymbol) new SimpleSymbol("txt_comandoIgnicao").readResolve());
    static final IntNum Lit211;
    static final FString Lit212 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit213 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit214 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal5").readResolve());
    static final IntNum Lit215;
    static final IntNum Lit216 = IntNum.make(-1023);
    static final FString Lit217 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit218 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit219 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal27").readResolve());
    static final SimpleSymbol Lit22 = ((SimpleSymbol) new SimpleSymbol("FirebaseDB1").readResolve());
    static final IntNum Lit220;
    static final IntNum Lit221 = IntNum.make(-1005);
    static final FString Lit222 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit223 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit224 = ((SimpleSymbol) new SimpleSymbol("btn_bloquear_combustivel").readResolve());
    static final IntNum Lit225;
    static final IntNum Lit226 = IntNum.make(-1022);
    static final IntNum Lit227 = IntNum.make(16777215);
    static final IntNum Lit228 = IntNum.make(-1035);
    static final FString Lit229 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit23 = ((SimpleSymbol) new SimpleSymbol("GetValue").readResolve());
    static final SimpleSymbol Lit230 = ((SimpleSymbol) new SimpleSymbol("btn_desbloquear_combustivel").readResolve());
    static final PairWithPosition Lit231 = PairWithPosition.make(Lit315, PairWithPosition.make(Lit315, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 1761408), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 1761403);
    static final PairWithPosition Lit232 = PairWithPosition.make(Lit9, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 1761916);
    static final SimpleSymbol Lit233 = ((SimpleSymbol) new SimpleSymbol("btn_bloquear_combustivel$Click").readResolve());
    static final FString Lit234 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit235 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal16").readResolve());
    static final IntNum Lit236;
    static final IntNum Lit237 = IntNum.make(-1020);
    static final FString Lit238 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit239 = new FString("com.google.appinventor.components.runtime.Button");
    static final IntNum Lit24 = IntNum.make(1);
    static final IntNum Lit240;
    static final IntNum Lit241 = IntNum.make(-1022);
    static final IntNum Lit242 = IntNum.make(16777215);
    static final IntNum Lit243 = IntNum.make(-1035);
    static final FString Lit244 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit245 = PairWithPosition.make(Lit315, PairWithPosition.make(Lit315, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 1876093), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 1876088);
    static final PairWithPosition Lit246;
    static final SimpleSymbol Lit247 = ((SimpleSymbol) new SimpleSymbol("btn_desbloquear_combustivel$Click").readResolve());
    static final FString Lit248 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit249 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal10").readResolve());
    static final PairWithPosition Lit25 = PairWithPosition.make(Lit316, PairWithPosition.make(Lit12, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 110808), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 110802);
    static final IntNum Lit250;
    static final FString Lit251 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit252 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit253 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal11").readResolve());
    static final IntNum Lit254;
    static final IntNum Lit255 = IntNum.make(-1014);
    static final FString Lit256 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit257 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit258 = ((SimpleSymbol) new SimpleSymbol("Legenda3").readResolve());
    static final IntNum Lit259;
    static final PairWithPosition Lit26 = PairWithPosition.make(Lit9, PairWithPosition.make(Lit9, PairWithPosition.make(Lit9, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 110857), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 110852), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 110846);
    static final IntNum Lit260;
    static final FString Lit261 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit262 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit263 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal12").readResolve());
    static final IntNum Lit264;
    static final IntNum Lit265 = IntNum.make(-1035);
    static final FString Lit266 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit267 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit268 = ((SimpleSymbol) new SimpleSymbol("Legenda4").readResolve());
    static final IntNum Lit269;
    static final IntNum Lit27 = IntNum.make(100);
    static final IntNum Lit270;
    static final FString Lit271 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit272 = new FString("com.google.appinventor.components.runtime.Notifier");
    static final FString Lit273 = new FString("com.google.appinventor.components.runtime.Notifier");
    static final FString Lit274 = new FString("com.google.appinventor.components.runtime.Texting");
    static final SimpleSymbol Lit275 = ((SimpleSymbol) new SimpleSymbol("ReceivingEnabled").readResolve());
    static final FString Lit276 = new FString("com.google.appinventor.components.runtime.Texting");
    static final FString Lit277 = new FString("com.google.appinventor.components.runtime.FirebaseDB");
    static final SimpleSymbol Lit278 = ((SimpleSymbol) new SimpleSymbol("DefaultURL").readResolve());
    static final SimpleSymbol Lit279 = ((SimpleSymbol) new SimpleSymbol("DeveloperBucket").readResolve());
    static final PairWithPosition Lit28 = PairWithPosition.make(Lit12, PairWithPosition.make(Lit12, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111007), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 110999);
    static final SimpleSymbol Lit280 = ((SimpleSymbol) new SimpleSymbol("FirebaseToken").readResolve());
    static final SimpleSymbol Lit281 = ((SimpleSymbol) new SimpleSymbol("FirebaseURL").readResolve());
    static final SimpleSymbol Lit282 = ((SimpleSymbol) new SimpleSymbol("ProjectBucket").readResolve());
    static final FString Lit283 = new FString("com.google.appinventor.components.runtime.FirebaseDB");
    static final IntNum Lit284 = IntNum.make(2);
    static final PairWithPosition Lit285 = PairWithPosition.make(Lit316, PairWithPosition.make(Lit12, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 2244755), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 2244749);
    static final PairWithPosition Lit286 = PairWithPosition.make(Lit315, PairWithPosition.make(Lit315, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 2244792), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 2244787);
    static final SimpleSymbol Lit287 = ((SimpleSymbol) new SimpleSymbol("$value").readResolve());
    static final IntNum Lit288 = IntNum.make(7);
    static final PairWithPosition Lit289 = PairWithPosition.make(Lit316, PairWithPosition.make(Lit12, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 2244924), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 2244918);
    static final PairWithPosition Lit29 = PairWithPosition.make(Lit12, PairWithPosition.make(Lit12, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111105), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111097);
    static final PairWithPosition Lit290 = PairWithPosition.make(Lit316, PairWithPosition.make(Lit12, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 2245096), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 2245090);
    static final PairWithPosition Lit291 = PairWithPosition.make(Lit315, PairWithPosition.make(Lit315, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 2245133), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 2245128);
    static final IntNum Lit292 = IntNum.make(9);
    static final PairWithPosition Lit293 = PairWithPosition.make(Lit316, PairWithPosition.make(Lit12, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 2245265), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 2245259);
    static final PairWithPosition Lit294 = PairWithPosition.make(Lit316, PairWithPosition.make(Lit12, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 2245437), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 2245431);
    static final IntNum Lit295 = IntNum.make(3);
    static final PairWithPosition Lit296 = PairWithPosition.make(Lit315, PairWithPosition.make(Lit315, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 2245474), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 2245469);
    static final IntNum Lit297 = IntNum.make(11);
    static final PairWithPosition Lit298;
    static final SimpleSymbol Lit299 = ((SimpleSymbol) new SimpleSymbol("FirebaseDB1$GotValue").readResolve());
    static final SimpleSymbol Lit3 = ((SimpleSymbol) new SimpleSymbol("g$Maquina").readResolve());
    static final PairWithPosition Lit30 = PairWithPosition.make(Lit12, PairWithPosition.make(Lit12, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111203), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111195);
    static final SimpleSymbol Lit300 = ((SimpleSymbol) new SimpleSymbol("GotValue").readResolve());
    static final SimpleSymbol Lit301 = ((SimpleSymbol) new SimpleSymbol("get-simple-name").readResolve());
    static final SimpleSymbol Lit302 = ((SimpleSymbol) new SimpleSymbol("android-log-form").readResolve());
    static final SimpleSymbol Lit303 = ((SimpleSymbol) new SimpleSymbol("add-to-form-environment").readResolve());
    static final SimpleSymbol Lit304 = ((SimpleSymbol) new SimpleSymbol("lookup-in-form-environment").readResolve());
    static final SimpleSymbol Lit305 = ((SimpleSymbol) new SimpleSymbol("is-bound-in-form-environment").readResolve());
    static final SimpleSymbol Lit306 = ((SimpleSymbol) new SimpleSymbol("add-to-global-var-environment").readResolve());
    static final SimpleSymbol Lit307 = ((SimpleSymbol) new SimpleSymbol("add-to-events").readResolve());
    static final SimpleSymbol Lit308 = ((SimpleSymbol) new SimpleSymbol("add-to-components").readResolve());
    static final SimpleSymbol Lit309 = ((SimpleSymbol) new SimpleSymbol("add-to-global-vars").readResolve());
    static final PairWithPosition Lit31 = PairWithPosition.make(Lit12, PairWithPosition.make(Lit12, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111301), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111293);
    static final SimpleSymbol Lit310 = ((SimpleSymbol) new SimpleSymbol("add-to-form-do-after-creation").readResolve());
    static final SimpleSymbol Lit311 = ((SimpleSymbol) new SimpleSymbol("send-error").readResolve());
    static final SimpleSymbol Lit312 = ((SimpleSymbol) new SimpleSymbol("dispatchEvent").readResolve());
    static final SimpleSymbol Lit313 = ((SimpleSymbol) new SimpleSymbol("dispatchGenericEvent").readResolve());
    static final SimpleSymbol Lit314 = ((SimpleSymbol) new SimpleSymbol("lookup-handler").readResolve());
    static final SimpleSymbol Lit315 = ((SimpleSymbol) new SimpleSymbol("any").readResolve());
    static final SimpleSymbol Lit316 = ((SimpleSymbol) new SimpleSymbol("list").readResolve());
    static final PairWithPosition Lit32 = PairWithPosition.make(Lit12, PairWithPosition.make(Lit12, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111399), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111391);
    static final PairWithPosition Lit33 = PairWithPosition.make(Lit12, PairWithPosition.make(Lit12, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111497), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111489);
    static final PairWithPosition Lit34 = PairWithPosition.make(Lit12, PairWithPosition.make(Lit12, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111595), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111587);
    static final PairWithPosition Lit35 = PairWithPosition.make(Lit12, PairWithPosition.make(Lit12, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111693), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111685);
    static final PairWithPosition Lit36 = PairWithPosition.make(Lit12, PairWithPosition.make(Lit12, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111791), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111783);
    static final PairWithPosition Lit37 = PairWithPosition.make(Lit12, PairWithPosition.make(Lit12, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111889), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111881);
    static final PairWithPosition Lit38 = PairWithPosition.make(Lit12, PairWithPosition.make(Lit12, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111987), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 111979);
    static final PairWithPosition Lit39 = PairWithPosition.make(Lit315, PairWithPosition.make(Lit315, PairWithPosition.make(Lit315, PairWithPosition.make(Lit315, PairWithPosition.make(Lit315, PairWithPosition.make(Lit315, PairWithPosition.make(Lit315, PairWithPosition.make(Lit315, PairWithPosition.make(Lit315, PairWithPosition.make(Lit315, PairWithPosition.make(Lit315, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 112057), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 112053), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 112049), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 112045), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 112041), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 112037), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 112033), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 112029), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 112025), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 112021), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 112016);
    static final IntNum Lit4 = IntNum.make(0);
    static final PairWithPosition Lit40 = PairWithPosition.make(Lit9, PairWithPosition.make(Lit315, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 112086), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 112080);
    static final SimpleSymbol Lit41 = ((SimpleSymbol) new SimpleSymbol("Comandos$Initialize").readResolve());
    static final SimpleSymbol Lit42 = ((SimpleSymbol) new SimpleSymbol("Initialize").readResolve());
    static final FString Lit43 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit44 = ((SimpleSymbol) new SimpleSymbol("Legenda7").readResolve());
    static final SimpleSymbol Lit45 = ((SimpleSymbol) new SimpleSymbol("Height").readResolve());
    static final IntNum Lit46 = IntNum.make(-1001);
    static final SimpleSymbol Lit47 = ((SimpleSymbol) new SimpleSymbol("TextColor").readResolve());
    static final IntNum Lit48 = IntNum.make(16777215);
    static final FString Lit49 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit5 = ((SimpleSymbol) new SimpleSymbol("g$c1").readResolve());
    static final FString Lit50 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit51 = ((SimpleSymbol) new SimpleSymbol("btn_voltarTela").readResolve());
    static final IntNum Lit52 = IntNum.make(16777215);
    static final IntNum Lit53 = IntNum.make(-1005);
    static final SimpleSymbol Lit54 = ((SimpleSymbol) new SimpleSymbol(Component.LISTVIEW_KEY_IMAGE).readResolve());
    static final IntNum Lit55 = IntNum.make(16777215);
    static final SimpleSymbol Lit56 = ((SimpleSymbol) new SimpleSymbol("Width").readResolve());
    static final IntNum Lit57 = IntNum.make(-1010);
    static final FString Lit58 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit59 = PairWithPosition.make(Lit316, PairWithPosition.make(Lit12, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 217269), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 217263);
    static final SimpleSymbol Lit6 = ((SimpleSymbol) new SimpleSymbol("ActionBar").readResolve());
    static final PairWithPosition Lit60 = PairWithPosition.make(Lit9, PairWithPosition.make(Lit315, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 217305), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 217299);
    static final SimpleSymbol Lit61 = ((SimpleSymbol) new SimpleSymbol("btn_voltarTela$Click").readResolve());
    static final SimpleSymbol Lit62 = ((SimpleSymbol) new SimpleSymbol("Click").readResolve());
    static final FString Lit63 = new FString("com.google.appinventor.components.runtime.VerticalScrollArrangement");
    static final SimpleSymbol Lit64 = ((SimpleSymbol) new SimpleSymbol("VerticalScrollArrangement1").readResolve());
    static final IntNum Lit65;
    static final IntNum Lit66 = IntNum.make(-2);
    static final FString Lit67 = new FString("com.google.appinventor.components.runtime.VerticalScrollArrangement");
    static final FString Lit68 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit69 = ((SimpleSymbol) new SimpleSymbol("txt_comandoBateria").readResolve());
    static final SimpleSymbol Lit7 = ((SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN).readResolve());
    static final IntNum Lit70;
    static final SimpleSymbol Lit71 = ((SimpleSymbol) new SimpleSymbol("FontBold").readResolve());
    static final SimpleSymbol Lit72 = ((SimpleSymbol) new SimpleSymbol("FontSize").readResolve());
    static final IntNum Lit73 = IntNum.make(20);
    static final SimpleSymbol Lit74 = ((SimpleSymbol) new SimpleSymbol("Text").readResolve());
    static final SimpleSymbol Lit75 = ((SimpleSymbol) new SimpleSymbol("TextAlignment").readResolve());
    static final IntNum Lit76;
    static final FString Lit77 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit78 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit79 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal1").readResolve());
    static final SimpleSymbol Lit8 = ((SimpleSymbol) new SimpleSymbol("AppName").readResolve());
    static final IntNum Lit80;
    static final IntNum Lit81 = IntNum.make(-1023);
    static final FString Lit82 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit83 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit84 = ((SimpleSymbol) new SimpleSymbol("OrganizaçãoHorizontal24").readResolve());
    static final IntNum Lit85;
    static final IntNum Lit86 = IntNum.make(-1005);
    static final FString Lit87 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit88 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit89 = ((SimpleSymbol) new SimpleSymbol("btn_bloquear_ignicao").readResolve());
    static final SimpleSymbol Lit9;
    static final IntNum Lit90;
    static final IntNum Lit91 = IntNum.make(14);
    static final IntNum Lit92 = IntNum.make(-1022);
    static final IntNum Lit93 = IntNum.make(16777215);
    static final IntNum Lit94 = IntNum.make(-1035);
    static final FString Lit95 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit96 = ((SimpleSymbol) new SimpleSymbol("MensagensSMS1").readResolve());
    static final SimpleSymbol Lit97 = ((SimpleSymbol) new SimpleSymbol("PhoneNumber").readResolve());
    static final SimpleSymbol Lit98 = ((SimpleSymbol) new SimpleSymbol("Message").readResolve());
    static final SimpleSymbol Lit99 = ((SimpleSymbol) new SimpleSymbol("SendMessageDirect").readResolve());
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
    static final ModuleMethod lambda$Fn49 = null;
    static final ModuleMethod lambda$Fn5 = null;
    static final ModuleMethod lambda$Fn50 = null;
    static final ModuleMethod lambda$Fn51 = null;
    static final ModuleMethod lambda$Fn52 = null;
    static final ModuleMethod lambda$Fn53 = null;
    static final ModuleMethod lambda$Fn54 = null;
    static final ModuleMethod lambda$Fn55 = null;
    static final ModuleMethod lambda$Fn56 = null;
    static final ModuleMethod lambda$Fn57 = null;
    static final ModuleMethod lambda$Fn58 = null;
    static final ModuleMethod lambda$Fn59 = null;
    static final ModuleMethod lambda$Fn6 = null;
    static final ModuleMethod lambda$Fn60 = null;
    static final ModuleMethod lambda$Fn61 = null;
    static final ModuleMethod lambda$Fn62 = null;
    static final ModuleMethod lambda$Fn63 = null;
    static final ModuleMethod lambda$Fn64 = null;
    static final ModuleMethod lambda$Fn65 = null;
    static final ModuleMethod lambda$Fn66 = null;
    static final ModuleMethod lambda$Fn67 = null;
    static final ModuleMethod lambda$Fn68 = null;
    static final ModuleMethod lambda$Fn69 = null;
    static final ModuleMethod lambda$Fn7 = null;
    static final ModuleMethod lambda$Fn70 = null;
    static final ModuleMethod lambda$Fn71 = null;
    static final ModuleMethod lambda$Fn72 = null;
    static final ModuleMethod lambda$Fn73 = null;
    static final ModuleMethod lambda$Fn74 = null;
    static final ModuleMethod lambda$Fn75 = null;
    static final ModuleMethod lambda$Fn76 = null;
    static final ModuleMethod lambda$Fn77 = null;
    static final ModuleMethod lambda$Fn78 = null;
    static final ModuleMethod lambda$Fn79 = null;
    static final ModuleMethod lambda$Fn8 = null;
    static final ModuleMethod lambda$Fn80 = null;
    static final ModuleMethod lambda$Fn81 = null;
    static final ModuleMethod lambda$Fn82 = null;
    static final ModuleMethod lambda$Fn83 = null;
    static final ModuleMethod lambda$Fn84 = null;
    static final ModuleMethod lambda$Fn9 = null;
    public Boolean $Stdebug$Mnform$St;
    public final ModuleMethod $define;
    public final ModuleMethod Comandos$Initialize;
    public FirebaseDB FirebaseDB1;
    public final ModuleMethod FirebaseDB1$GotValue;
    public Label Legenda1;
    public Label Legenda2;
    public Label Legenda3;
    public Label Legenda4;
    public Label Legenda5;
    public Label Legenda6;
    public Label Legenda7;
    public Texting MensagensSMS1;
    public Notifier Notificador1;

    /* renamed from: OrganizaçãoHorizontal1  reason: contains not printable characters */
    public HorizontalArrangement f392OrganizaoHorizontal1;

    /* renamed from: OrganizaçãoHorizontal10  reason: contains not printable characters */
    public HorizontalArrangement f393OrganizaoHorizontal10;

    /* renamed from: OrganizaçãoHorizontal11  reason: contains not printable characters */
    public HorizontalArrangement f394OrganizaoHorizontal11;

    /* renamed from: OrganizaçãoHorizontal12  reason: contains not printable characters */
    public HorizontalArrangement f395OrganizaoHorizontal12;

    /* renamed from: OrganizaçãoHorizontal13  reason: contains not printable characters */
    public HorizontalArrangement f396OrganizaoHorizontal13;

    /* renamed from: OrganizaçãoHorizontal14  reason: contains not printable characters */
    public HorizontalArrangement f397OrganizaoHorizontal14;

    /* renamed from: OrganizaçãoHorizontal15  reason: contains not printable characters */
    public HorizontalArrangement f398OrganizaoHorizontal15;

    /* renamed from: OrganizaçãoHorizontal16  reason: contains not printable characters */
    public HorizontalArrangement f399OrganizaoHorizontal16;

    /* renamed from: OrganizaçãoHorizontal24  reason: contains not printable characters */
    public HorizontalArrangement f400OrganizaoHorizontal24;

    /* renamed from: OrganizaçãoHorizontal26  reason: contains not printable characters */
    public HorizontalArrangement f401OrganizaoHorizontal26;

    /* renamed from: OrganizaçãoHorizontal27  reason: contains not printable characters */
    public HorizontalArrangement f402OrganizaoHorizontal27;

    /* renamed from: OrganizaçãoHorizontal28  reason: contains not printable characters */
    public HorizontalArrangement f403OrganizaoHorizontal28;

    /* renamed from: OrganizaçãoHorizontal29  reason: contains not printable characters */
    public HorizontalArrangement f404OrganizaoHorizontal29;

    /* renamed from: OrganizaçãoHorizontal3  reason: contains not printable characters */
    public HorizontalArrangement f405OrganizaoHorizontal3;

    /* renamed from: OrganizaçãoHorizontal4  reason: contains not printable characters */
    public HorizontalArrangement f406OrganizaoHorizontal4;

    /* renamed from: OrganizaçãoHorizontal5  reason: contains not printable characters */
    public HorizontalArrangement f407OrganizaoHorizontal5;

    /* renamed from: OrganizaçãoHorizontal6  reason: contains not printable characters */
    public HorizontalArrangement f408OrganizaoHorizontal6;

    /* renamed from: OrganizaçãoHorizontal7  reason: contains not printable characters */
    public HorizontalArrangement f409OrganizaoHorizontal7;

    /* renamed from: OrganizaçãoHorizontal8  reason: contains not printable characters */
    public HorizontalArrangement f410OrganizaoHorizontal8;

    /* renamed from: OrganizaçãoHorizontal9  reason: contains not printable characters */
    public HorizontalArrangement f411OrganizaoHorizontal9;
    public VerticalScrollArrangement VerticalScrollArrangement1;
    public final ModuleMethod add$Mnto$Mncomponents;
    public final ModuleMethod add$Mnto$Mnevents;
    public final ModuleMethod add$Mnto$Mnform$Mndo$Mnafter$Mncreation;
    public final ModuleMethod add$Mnto$Mnform$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvar$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvars;
    public final ModuleMethod android$Mnlog$Mnform;
    public Button btn_bloquear_bateria1;
    public final ModuleMethod btn_bloquear_bateria1$Click;
    public Button btn_bloquear_combustivel;
    public final ModuleMethod btn_bloquear_combustivel$Click;
    public Button btn_bloquear_ignicao;
    public final ModuleMethod btn_bloquear_ignicao$Click;
    public Button btn_desbloquear_bateria1;
    public final ModuleMethod btn_desbloquear_bateria1$Click;
    public Button btn_desbloquear_combustivel;
    public final ModuleMethod btn_desbloquear_combustivel$Click;
    public Button btn_desbloquear_ignicao;
    public final ModuleMethod btn_desbloquear_ignicao$Click;
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
    public final ModuleMethod is$Mnbound$Mnin$Mnform$Mnenvironment;
    public final ModuleMethod lookup$Mnhandler;
    public final ModuleMethod lookup$Mnin$Mnform$Mnenvironment;
    public final ModuleMethod onCreate;
    public final ModuleMethod process$Mnexception;
    public final ModuleMethod send$Mnerror;
    public Label txt_comandoBateria;
    public Label txt_comandoCombustivel;
    public Label txt_comandoIgnicao;

    static {
        SimpleSymbol simpleSymbol = Lit316;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("number").readResolve();
        Lit12 = simpleSymbol2;
        Lit298 = PairWithPosition.make(simpleSymbol, PairWithPosition.make(simpleSymbol2, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 2245607), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 2245601);
        int[] iArr = new int[2];
        iArr[0] = -1;
        Lit270 = IntNum.make(iArr);
        int[] iArr2 = new int[2];
        iArr2[0] = -12303292;
        Lit269 = IntNum.make(iArr2);
        int[] iArr3 = new int[2];
        iArr3[0] = -12303292;
        Lit264 = IntNum.make(iArr3);
        int[] iArr4 = new int[2];
        iArr4[0] = -1;
        Lit260 = IntNum.make(iArr4);
        int[] iArr5 = new int[2];
        iArr5[0] = -12303292;
        Lit259 = IntNum.make(iArr5);
        int[] iArr6 = new int[2];
        iArr6[0] = -12303292;
        Lit254 = IntNum.make(iArr6);
        int[] iArr7 = new int[2];
        iArr7[0] = -12303292;
        Lit250 = IntNum.make(iArr7);
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_TEXT).readResolve();
        Lit9 = simpleSymbol3;
        Lit246 = PairWithPosition.make(simpleSymbol3, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/Comandos.yail", 1876599);
        int[] iArr8 = new int[2];
        iArr8[0] = -12303292;
        Lit240 = IntNum.make(iArr8);
        int[] iArr9 = new int[2];
        iArr9[0] = -12303292;
        Lit236 = IntNum.make(iArr9);
        int[] iArr10 = new int[2];
        iArr10[0] = -12303292;
        Lit225 = IntNum.make(iArr10);
        int[] iArr11 = new int[2];
        iArr11[0] = -12303292;
        Lit220 = IntNum.make(iArr11);
        int[] iArr12 = new int[2];
        iArr12[0] = -12303292;
        Lit215 = IntNum.make(iArr12);
        int[] iArr13 = new int[2];
        iArr13[0] = -14336;
        Lit211 = IntNum.make(iArr13);
        int[] iArr14 = new int[2];
        iArr14[0] = -12303292;
        Lit207 = IntNum.make(iArr14);
        int[] iArr15 = new int[2];
        iArr15[0] = -1;
        Lit203 = IntNum.make(iArr15);
        int[] iArr16 = new int[2];
        iArr16[0] = -12303292;
        Lit202 = IntNum.make(iArr16);
        int[] iArr17 = new int[2];
        iArr17[0] = -12303292;
        Lit197 = IntNum.make(iArr17);
        int[] iArr18 = new int[2];
        iArr18[0] = -1;
        Lit193 = IntNum.make(iArr18);
        int[] iArr19 = new int[2];
        iArr19[0] = -12303292;
        Lit192 = IntNum.make(iArr19);
        int[] iArr20 = new int[2];
        iArr20[0] = -12303292;
        Lit187 = IntNum.make(iArr20);
        int[] iArr21 = new int[2];
        iArr21[0] = -12303292;
        Lit183 = IntNum.make(iArr21);
        int[] iArr22 = new int[2];
        iArr22[0] = -12303292;
        Lit173 = IntNum.make(iArr22);
        int[] iArr23 = new int[2];
        iArr23[0] = -12303292;
        Lit169 = IntNum.make(iArr23);
        int[] iArr24 = new int[2];
        iArr24[0] = -12303292;
        Lit156 = IntNum.make(iArr24);
        int[] iArr25 = new int[2];
        iArr25[0] = -12303292;
        Lit151 = IntNum.make(iArr25);
        int[] iArr26 = new int[2];
        iArr26[0] = -12303292;
        Lit146 = IntNum.make(iArr26);
        int[] iArr27 = new int[2];
        iArr27[0] = -14336;
        Lit142 = IntNum.make(iArr27);
        int[] iArr28 = new int[2];
        iArr28[0] = -12303292;
        Lit138 = IntNum.make(iArr28);
        int[] iArr29 = new int[2];
        iArr29[0] = -1;
        Lit134 = IntNum.make(iArr29);
        int[] iArr30 = new int[2];
        iArr30[0] = -12303292;
        Lit133 = IntNum.make(iArr30);
        int[] iArr31 = new int[2];
        iArr31[0] = -12303292;
        Lit128 = IntNum.make(iArr31);
        int[] iArr32 = new int[2];
        iArr32[0] = -1;
        Lit124 = IntNum.make(iArr32);
        int[] iArr33 = new int[2];
        iArr33[0] = -12303292;
        Lit119 = IntNum.make(iArr33);
        int[] iArr34 = new int[2];
        iArr34[0] = -12303292;
        Lit115 = IntNum.make(iArr34);
        int[] iArr35 = new int[2];
        iArr35[0] = -12303292;
        Lit103 = IntNum.make(iArr35);
        int[] iArr36 = new int[2];
        iArr36[0] = -12303292;
        Lit90 = IntNum.make(iArr36);
        int[] iArr37 = new int[2];
        iArr37[0] = -12303292;
        Lit85 = IntNum.make(iArr37);
        int[] iArr38 = new int[2];
        iArr38[0] = -12303292;
        Lit80 = IntNum.make(iArr38);
        int[] iArr39 = new int[2];
        iArr39[0] = -14336;
        Lit76 = IntNum.make(iArr39);
        int[] iArr40 = new int[2];
        iArr40[0] = -12303292;
        Lit70 = IntNum.make(iArr40);
        int[] iArr41 = new int[2];
        iArr41[0] = -12303292;
        Lit65 = IntNum.make(iArr41);
        int[] iArr42 = new int[2];
        iArr42[0] = -12303292;
        Lit11 = IntNum.make(iArr42);
    }

    public Comandos() {
        ModuleInfo.register(this);
        frame frame2 = new frame();
        frame2.$main = this;
        this.get$Mnsimple$Mnname = new ModuleMethod(frame2, 1, Lit301, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.onCreate = new ModuleMethod(frame2, 2, "onCreate", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.android$Mnlog$Mnform = new ModuleMethod(frame2, 3, Lit302, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnform$Mnenvironment = new ModuleMethod(frame2, 4, Lit303, 8194);
        this.lookup$Mnin$Mnform$Mnenvironment = new ModuleMethod(frame2, 5, Lit304, 8193);
        this.is$Mnbound$Mnin$Mnform$Mnenvironment = new ModuleMethod(frame2, 7, Lit305, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = new ModuleMethod(frame2, 8, Lit306, 8194);
        this.add$Mnto$Mnevents = new ModuleMethod(frame2, 9, Lit307, 8194);
        this.add$Mnto$Mncomponents = new ModuleMethod(frame2, 10, Lit308, 16388);
        this.add$Mnto$Mnglobal$Mnvars = new ModuleMethod(frame2, 11, Lit309, 8194);
        this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = new ModuleMethod(frame2, 12, Lit310, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.send$Mnerror = new ModuleMethod(frame2, 13, Lit311, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.process$Mnexception = new ModuleMethod(frame2, 14, "process-exception", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.dispatchEvent = new ModuleMethod(frame2, 15, Lit312, 16388);
        this.dispatchGenericEvent = new ModuleMethod(frame2, 16, Lit313, 16388);
        this.lookup$Mnhandler = new ModuleMethod(frame2, 17, Lit314, 8194);
        ModuleMethod moduleMethod = new ModuleMethod(frame2, 18, (Object) null, 0);
        moduleMethod.setProperty("source-location", "/tmp/runtime6229131719407907284.scm:627");
        lambda$Fn1 = moduleMethod;
        this.$define = new ModuleMethod(frame2, 19, "$define", 0);
        lambda$Fn2 = new ModuleMethod(frame2, 20, (Object) null, 0);
        lambda$Fn3 = new ModuleMethod(frame2, 21, (Object) null, 0);
        lambda$Fn4 = new ModuleMethod(frame2, 22, (Object) null, 0);
        this.Comandos$Initialize = new ModuleMethod(frame2, 23, Lit41, 0);
        lambda$Fn5 = new ModuleMethod(frame2, 24, (Object) null, 0);
        lambda$Fn6 = new ModuleMethod(frame2, 25, (Object) null, 0);
        lambda$Fn7 = new ModuleMethod(frame2, 26, (Object) null, 0);
        lambda$Fn8 = new ModuleMethod(frame2, 27, (Object) null, 0);
        this.btn_voltarTela$Click = new ModuleMethod(frame2, 28, Lit61, 0);
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
        this.btn_bloquear_ignicao$Click = new ModuleMethod(frame2, 39, Lit100, 0);
        lambda$Fn19 = new ModuleMethod(frame2, 40, (Object) null, 0);
        lambda$Fn20 = new ModuleMethod(frame2, 41, (Object) null, 0);
        lambda$Fn21 = new ModuleMethod(frame2, 42, (Object) null, 0);
        lambda$Fn22 = new ModuleMethod(frame2, 43, (Object) null, 0);
        this.btn_desbloquear_ignicao$Click = new ModuleMethod(frame2, 44, Lit112, 0);
        lambda$Fn23 = new ModuleMethod(frame2, 45, (Object) null, 0);
        lambda$Fn24 = new ModuleMethod(frame2, 46, (Object) null, 0);
        lambda$Fn25 = new ModuleMethod(frame2, 47, (Object) null, 0);
        lambda$Fn26 = new ModuleMethod(frame2, 48, (Object) null, 0);
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
        lambda$Fn37 = new ModuleMethod(frame2, 59, (Object) null, 0);
        lambda$Fn38 = new ModuleMethod(frame2, 60, (Object) null, 0);
        lambda$Fn39 = new ModuleMethod(frame2, 61, (Object) null, 0);
        lambda$Fn40 = new ModuleMethod(frame2, 62, (Object) null, 0);
        lambda$Fn41 = new ModuleMethod(frame2, 63, (Object) null, 0);
        lambda$Fn42 = new ModuleMethod(frame2, 64, (Object) null, 0);
        this.btn_bloquear_bateria1$Click = new ModuleMethod(frame2, 65, Lit166, 0);
        lambda$Fn43 = new ModuleMethod(frame2, 66, (Object) null, 0);
        lambda$Fn44 = new ModuleMethod(frame2, 67, (Object) null, 0);
        lambda$Fn45 = new ModuleMethod(frame2, 68, (Object) null, 0);
        lambda$Fn46 = new ModuleMethod(frame2, 69, (Object) null, 0);
        this.btn_desbloquear_bateria1$Click = new ModuleMethod(frame2, 70, Lit180, 0);
        lambda$Fn47 = new ModuleMethod(frame2, 71, (Object) null, 0);
        lambda$Fn48 = new ModuleMethod(frame2, 72, (Object) null, 0);
        lambda$Fn49 = new ModuleMethod(frame2, 73, (Object) null, 0);
        lambda$Fn50 = new ModuleMethod(frame2, 74, (Object) null, 0);
        lambda$Fn51 = new ModuleMethod(frame2, 75, (Object) null, 0);
        lambda$Fn52 = new ModuleMethod(frame2, 76, (Object) null, 0);
        lambda$Fn53 = new ModuleMethod(frame2, 77, (Object) null, 0);
        lambda$Fn54 = new ModuleMethod(frame2, 78, (Object) null, 0);
        lambda$Fn55 = new ModuleMethod(frame2, 79, (Object) null, 0);
        lambda$Fn56 = new ModuleMethod(frame2, 80, (Object) null, 0);
        lambda$Fn57 = new ModuleMethod(frame2, 81, (Object) null, 0);
        lambda$Fn58 = new ModuleMethod(frame2, 82, (Object) null, 0);
        lambda$Fn59 = new ModuleMethod(frame2, 83, (Object) null, 0);
        lambda$Fn60 = new ModuleMethod(frame2, 84, (Object) null, 0);
        lambda$Fn61 = new ModuleMethod(frame2, 85, (Object) null, 0);
        lambda$Fn62 = new ModuleMethod(frame2, 86, (Object) null, 0);
        lambda$Fn63 = new ModuleMethod(frame2, 87, (Object) null, 0);
        lambda$Fn64 = new ModuleMethod(frame2, 88, (Object) null, 0);
        lambda$Fn65 = new ModuleMethod(frame2, 89, (Object) null, 0);
        lambda$Fn66 = new ModuleMethod(frame2, 90, (Object) null, 0);
        this.btn_bloquear_combustivel$Click = new ModuleMethod(frame2, 91, Lit233, 0);
        lambda$Fn67 = new ModuleMethod(frame2, 92, (Object) null, 0);
        lambda$Fn68 = new ModuleMethod(frame2, 93, (Object) null, 0);
        lambda$Fn69 = new ModuleMethod(frame2, 94, (Object) null, 0);
        lambda$Fn70 = new ModuleMethod(frame2, 95, (Object) null, 0);
        this.btn_desbloquear_combustivel$Click = new ModuleMethod(frame2, 96, Lit247, 0);
        lambda$Fn71 = new ModuleMethod(frame2, 97, (Object) null, 0);
        lambda$Fn72 = new ModuleMethod(frame2, 98, (Object) null, 0);
        lambda$Fn73 = new ModuleMethod(frame2, 99, (Object) null, 0);
        lambda$Fn74 = new ModuleMethod(frame2, 100, (Object) null, 0);
        lambda$Fn75 = new ModuleMethod(frame2, 101, (Object) null, 0);
        lambda$Fn76 = new ModuleMethod(frame2, 102, (Object) null, 0);
        lambda$Fn77 = new ModuleMethod(frame2, 103, (Object) null, 0);
        lambda$Fn78 = new ModuleMethod(frame2, 104, (Object) null, 0);
        lambda$Fn79 = new ModuleMethod(frame2, 105, (Object) null, 0);
        lambda$Fn80 = new ModuleMethod(frame2, 106, (Object) null, 0);
        lambda$Fn81 = new ModuleMethod(frame2, 107, (Object) null, 0);
        lambda$Fn82 = new ModuleMethod(frame2, 108, (Object) null, 0);
        lambda$Fn83 = new ModuleMethod(frame2, 109, (Object) null, 0);
        lambda$Fn84 = new ModuleMethod(frame2, 110, (Object) null, 0);
        this.FirebaseDB1$GotValue = new ModuleMethod(frame2, 111, Lit299, 8194);
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
        Comandos = null;
        this.form$Mnname$Mnsymbol = Lit0;
        this.events$Mnto$Mnregister = LList.Empty;
        this.components$Mnto$Mncreate = LList.Empty;
        this.global$Mnvars$Mnto$Mncreate = LList.Empty;
        this.form$Mndo$Mnafter$Mncreation = LList.Empty;
        C0718runtime.$instance.run();
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addGlobalVarToCurrentFormEnvironment(Lit3, Lit4), $result);
        } else {
            addToGlobalVars(Lit3, lambda$Fn2);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addGlobalVarToCurrentFormEnvironment(Lit5, C0718runtime.callYailPrimitive(C0718runtime.get$Mnstart$Mnvalue, LList.Empty, LList.Empty, "get start value")), $result);
        } else {
            addToGlobalVars(Lit5, lambda$Fn3);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit6, Boolean.TRUE, Lit7);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit8, "EVA_imobilizer", Lit9);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit10, Lit11, Lit12);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit13, "slidevertical", Lit9);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit14, "slidevertical", Lit9);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit15, "user", Lit9);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit16, Boolean.TRUE, Lit7);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit17, Boolean.TRUE, Lit7);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit18, Boolean.FALSE, Lit7);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit19, "Responsive", Lit9);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit20, "AppTheme", Lit9);
            Values.writeValues(C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit21, "Comandos de seu equipamento", Lit9), $result);
        } else {
            addToFormDoAfterCreation(new Promise(lambda$Fn4));
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit41, this.Comandos$Initialize);
        } else {
            addToFormEnvironment(Lit41, this.Comandos$Initialize);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "Comandos", "Initialize");
        } else {
            addToEvents(Lit0, Lit42);
        }
        this.Legenda7 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit43, Lit44, lambda$Fn5), $result);
        } else {
            addToComponents(Lit0, Lit49, Lit44, lambda$Fn6);
        }
        this.btn_voltarTela = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit50, Lit51, lambda$Fn7), $result);
        } else {
            addToComponents(Lit0, Lit58, Lit51, lambda$Fn8);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit61, this.btn_voltarTela$Click);
        } else {
            addToFormEnvironment(Lit61, this.btn_voltarTela$Click);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "btn_voltarTela", "Click");
        } else {
            addToEvents(Lit51, Lit62);
        }
        this.VerticalScrollArrangement1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit63, Lit64, lambda$Fn9), $result);
        } else {
            addToComponents(Lit0, Lit67, Lit64, lambda$Fn10);
        }
        this.txt_comandoBateria = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit64, Lit68, Lit69, lambda$Fn11), $result);
        } else {
            addToComponents(Lit64, Lit77, Lit69, lambda$Fn12);
        }
        this.f392OrganizaoHorizontal1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit64, Lit78, Lit79, lambda$Fn13), $result);
        } else {
            addToComponents(Lit64, Lit82, Lit79, lambda$Fn14);
        }
        this.f400OrganizaoHorizontal24 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit79, Lit83, Lit84, lambda$Fn15), $result);
        } else {
            addToComponents(Lit79, Lit87, Lit84, lambda$Fn16);
        }
        this.btn_bloquear_ignicao = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit79, Lit88, Lit89, lambda$Fn17), $result);
        } else {
            addToComponents(Lit79, Lit95, Lit89, lambda$Fn18);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit100, this.btn_bloquear_ignicao$Click);
        } else {
            addToFormEnvironment(Lit100, this.btn_bloquear_ignicao$Click);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "btn_bloquear_ignicao", "Click");
        } else {
            addToEvents(Lit89, Lit62);
        }
        this.f406OrganizaoHorizontal4 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit79, Lit101, Lit102, lambda$Fn19), $result);
        } else {
            addToComponents(Lit79, Lit105, Lit102, lambda$Fn20);
        }
        this.btn_desbloquear_ignicao = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit79, Lit106, Lit107, lambda$Fn21), $result);
        } else {
            addToComponents(Lit79, Lit111, Lit107, lambda$Fn22);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit112, this.btn_desbloquear_ignicao$Click);
        } else {
            addToFormEnvironment(Lit112, this.btn_desbloquear_ignicao$Click);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "btn_desbloquear_ignicao", "Click");
        } else {
            addToEvents(Lit107, Lit62);
        }
        this.f409OrganizaoHorizontal7 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit64, Lit113, Lit114, lambda$Fn23), $result);
        } else {
            addToComponents(Lit64, Lit116, Lit114, lambda$Fn24);
        }
        this.f411OrganizaoHorizontal9 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit114, Lit117, Lit118, lambda$Fn25), $result);
        } else {
            addToComponents(Lit114, Lit121, Lit118, lambda$Fn26);
        }
        this.Legenda1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit114, Lit122, Lit123, lambda$Fn27), $result);
        } else {
            addToComponents(Lit114, Lit125, Lit123, lambda$Fn28);
        }
        this.f410OrganizaoHorizontal8 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit114, Lit126, Lit127, lambda$Fn29), $result);
        } else {
            addToComponents(Lit114, Lit130, Lit127, lambda$Fn30);
        }
        this.Legenda2 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit114, Lit131, Lit132, lambda$Fn31), $result);
        } else {
            addToComponents(Lit114, Lit135, Lit132, lambda$Fn32);
        }
        this.f403OrganizaoHorizontal28 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit64, Lit136, Lit137, lambda$Fn33), $result);
        } else {
            addToComponents(Lit64, Lit139, Lit137, lambda$Fn34);
        }
        this.txt_comandoCombustivel = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit64, Lit140, Lit141, lambda$Fn35), $result);
        } else {
            addToComponents(Lit64, Lit143, Lit141, lambda$Fn36);
        }
        this.f405OrganizaoHorizontal3 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit64, Lit144, Lit145, lambda$Fn37), $result);
        } else {
            addToComponents(Lit64, Lit148, Lit145, lambda$Fn38);
        }
        this.f401OrganizaoHorizontal26 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit145, Lit149, Lit150, lambda$Fn39), $result);
        } else {
            addToComponents(Lit145, Lit153, Lit150, lambda$Fn40);
        }
        this.btn_bloquear_bateria1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit145, Lit154, Lit155, lambda$Fn41), $result);
        } else {
            addToComponents(Lit145, Lit160, Lit155, lambda$Fn42);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit166, this.btn_bloquear_bateria1$Click);
        } else {
            addToFormEnvironment(Lit166, this.btn_bloquear_bateria1$Click);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "btn_bloquear_bateria1", "Click");
        } else {
            addToEvents(Lit155, Lit62);
        }
        this.f408OrganizaoHorizontal6 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit145, Lit167, Lit168, lambda$Fn43), $result);
        } else {
            addToComponents(Lit145, Lit171, Lit168, lambda$Fn44);
        }
        this.btn_desbloquear_bateria1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit145, Lit172, Lit161, lambda$Fn45), $result);
        } else {
            addToComponents(Lit145, Lit177, Lit161, lambda$Fn46);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit180, this.btn_desbloquear_bateria1$Click);
        } else {
            addToFormEnvironment(Lit180, this.btn_desbloquear_bateria1$Click);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "btn_desbloquear_bateria1", "Click");
        } else {
            addToEvents(Lit161, Lit62);
        }
        this.f396OrganizaoHorizontal13 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit64, Lit181, Lit182, lambda$Fn47), $result);
        } else {
            addToComponents(Lit64, Lit184, Lit182, lambda$Fn48);
        }
        this.f397OrganizaoHorizontal14 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit182, Lit185, Lit186, lambda$Fn49), $result);
        } else {
            addToComponents(Lit182, Lit189, Lit186, lambda$Fn50);
        }
        this.Legenda5 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit182, Lit190, Lit191, lambda$Fn51), $result);
        } else {
            addToComponents(Lit182, Lit194, Lit191, lambda$Fn52);
        }
        this.f398OrganizaoHorizontal15 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit182, Lit195, Lit196, lambda$Fn53), $result);
        } else {
            addToComponents(Lit182, Lit199, Lit196, lambda$Fn54);
        }
        this.Legenda6 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit182, Lit200, Lit201, lambda$Fn55), $result);
        } else {
            addToComponents(Lit182, Lit204, Lit201, lambda$Fn56);
        }
        this.f404OrganizaoHorizontal29 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit64, Lit205, Lit206, lambda$Fn57), $result);
        } else {
            addToComponents(Lit64, Lit208, Lit206, lambda$Fn58);
        }
        this.txt_comandoIgnicao = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit64, Lit209, Lit210, lambda$Fn59), $result);
        } else {
            addToComponents(Lit64, Lit212, Lit210, lambda$Fn60);
        }
        this.f407OrganizaoHorizontal5 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit64, Lit213, Lit214, lambda$Fn61), $result);
        } else {
            addToComponents(Lit64, Lit217, Lit214, lambda$Fn62);
        }
        this.f402OrganizaoHorizontal27 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit214, Lit218, Lit219, lambda$Fn63), $result);
        } else {
            addToComponents(Lit214, Lit222, Lit219, lambda$Fn64);
        }
        this.btn_bloquear_combustivel = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit214, Lit223, Lit224, lambda$Fn65), $result);
        } else {
            addToComponents(Lit214, Lit229, Lit224, lambda$Fn66);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit233, this.btn_bloquear_combustivel$Click);
        } else {
            addToFormEnvironment(Lit233, this.btn_bloquear_combustivel$Click);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "btn_bloquear_combustivel", "Click");
        } else {
            addToEvents(Lit224, Lit62);
        }
        this.f399OrganizaoHorizontal16 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit214, Lit234, Lit235, lambda$Fn67), $result);
        } else {
            addToComponents(Lit214, Lit238, Lit235, lambda$Fn68);
        }
        this.btn_desbloquear_combustivel = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit214, Lit239, Lit230, lambda$Fn69), $result);
        } else {
            addToComponents(Lit214, Lit244, Lit230, lambda$Fn70);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit247, this.btn_desbloquear_combustivel$Click);
        } else {
            addToFormEnvironment(Lit247, this.btn_desbloquear_combustivel$Click);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "btn_desbloquear_combustivel", "Click");
        } else {
            addToEvents(Lit230, Lit62);
        }
        this.f393OrganizaoHorizontal10 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit64, Lit248, Lit249, lambda$Fn71), $result);
        } else {
            addToComponents(Lit64, Lit251, Lit249, lambda$Fn72);
        }
        this.f394OrganizaoHorizontal11 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit249, Lit252, Lit253, lambda$Fn73), $result);
        } else {
            addToComponents(Lit249, Lit256, Lit253, lambda$Fn74);
        }
        this.Legenda3 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit249, Lit257, Lit258, lambda$Fn75), $result);
        } else {
            addToComponents(Lit249, Lit261, Lit258, lambda$Fn76);
        }
        this.f395OrganizaoHorizontal12 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit249, Lit262, Lit263, lambda$Fn77), $result);
        } else {
            addToComponents(Lit249, Lit266, Lit263, lambda$Fn78);
        }
        this.Legenda4 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit249, Lit267, Lit268, lambda$Fn79), $result);
        } else {
            addToComponents(Lit249, Lit271, Lit268, lambda$Fn80);
        }
        this.Notificador1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit272, Lit163, Boolean.FALSE), $result);
        } else {
            addToComponents(Lit0, Lit273, Lit163, Boolean.FALSE);
        }
        this.MensagensSMS1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit274, Lit96, lambda$Fn81), $result);
        } else {
            addToComponents(Lit0, Lit276, Lit96, lambda$Fn82);
        }
        this.FirebaseDB1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit277, Lit22, lambda$Fn83), $result);
        } else {
            addToComponents(Lit0, Lit283, Lit22, lambda$Fn84);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit299, this.FirebaseDB1$GotValue);
        } else {
            addToFormEnvironment(Lit299, this.FirebaseDB1$GotValue);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "FirebaseDB1", "GotValue");
        } else {
            addToEvents(Lit22, Lit300);
        }
        C0718runtime.initRuntime();
    }

    static IntNum lambda3() {
        return Lit4;
    }

    static Object lambda4() {
        return C0718runtime.callYailPrimitive(C0718runtime.get$Mnstart$Mnvalue, LList.Empty, LList.Empty, "get start value");
    }

    static Object lambda5() {
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit6, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit8, "EVA_imobilizer", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit10, Lit11, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit13, "slidevertical", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit14, "slidevertical", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit15, "user", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit16, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit17, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit18, Boolean.FALSE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit19, "Responsive", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit20, "AppTheme", Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit21, "Comandos de seu equipamento", Lit9);
    }

    public Object Comandos$Initialize() {
        C0718runtime.setThisForm();
        SimpleSymbol simpleSymbol = Lit22;
        SimpleSymbol simpleSymbol2 = Lit23;
        Object callYailPrimitive = C0718runtime.callYailPrimitive(C0718runtime.string$Mnreplace$Mnall, LList.list3(C0718runtime.callYailPrimitive(C0718runtime.yail$Mnlist$Mnget$Mnitem, LList.list2(C0718runtime.lookupGlobalVarInCurrentFormEnvironment(Lit5, C0718runtime.$Stthe$Mnnull$Mnvalue$St), Lit24), Lit25, "select list item"), ".", "_"), Lit26, "replace all");
        ModuleMethod moduleMethod = C0718runtime.make$Mnyail$Mnlist;
        Pair list1 = LList.list1(C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit24, Lit27), Lit28, "random integer"));
        LList.chain1(LList.chain1(LList.chain4(LList.chain4(list1, C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit24, Lit27), Lit29, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit24, Lit27), Lit30, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit24, Lit27), Lit31, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit24, Lit27), Lit32, "random integer")), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit24, Lit27), Lit33, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit24, Lit27), Lit34, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit24, Lit27), Lit35, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit24, Lit27), Lit36, "random integer")), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit24, Lit27), Lit37, "random integer")), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit24, Lit27), Lit38, "random integer"));
        return C0718runtime.callComponentMethod(simpleSymbol, simpleSymbol2, LList.list2(callYailPrimitive, C0718runtime.callYailPrimitive(moduleMethod, list1, Lit39, "make a list")), Lit40);
    }

    static Object lambda6() {
        C0718runtime.setAndCoerceProperty$Ex(Lit44, Lit45, Lit46, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit44, Lit47, Lit48, Lit12);
    }

    static Object lambda7() {
        C0718runtime.setAndCoerceProperty$Ex(Lit44, Lit45, Lit46, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit44, Lit47, Lit48, Lit12);
    }

    static Object lambda8() {
        C0718runtime.setAndCoerceProperty$Ex(Lit51, Lit10, Lit52, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit51, Lit45, Lit53, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit51, Lit54, "setaCorreta-removebg-preview.png", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit51, Lit47, Lit55, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit51, Lit56, Lit57, Lit12);
    }

    static Object lambda9() {
        C0718runtime.setAndCoerceProperty$Ex(Lit51, Lit10, Lit52, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit51, Lit45, Lit53, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit51, Lit54, "setaCorreta-removebg-preview.png", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit51, Lit47, Lit55, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit51, Lit56, Lit57, Lit12);
    }

    public Object btn_voltarTela$Click() {
        C0718runtime.setThisForm();
        return C0718runtime.callYailPrimitive(C0718runtime.open$Mnanother$Mnscreen$Mnwith$Mnstart$Mnvalue, LList.list2("Maquinario", C0718runtime.callYailPrimitive(C0718runtime.yail$Mnlist$Mnget$Mnitem, LList.list2(C0718runtime.lookupGlobalVarInCurrentFormEnvironment(Lit5, C0718runtime.$Stthe$Mnnull$Mnvalue$St), Lit24), Lit59, "select list item")), Lit60, "open another screen with start value");
    }

    static Object lambda10() {
        C0718runtime.setAndCoerceProperty$Ex(Lit64, Lit10, Lit65, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit64, Lit56, Lit66, Lit12);
    }

    static Object lambda11() {
        C0718runtime.setAndCoerceProperty$Ex(Lit64, Lit10, Lit65, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit64, Lit56, Lit66, Lit12);
    }

    static Object lambda12() {
        C0718runtime.setAndCoerceProperty$Ex(Lit69, Lit10, Lit70, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit69, Lit71, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit69, Lit72, Lit73, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit69, Lit74, "Bloqueio da Ignição", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit69, Lit75, Lit24, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit69, Lit47, Lit76, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit69, Lit56, Lit66, Lit12);
    }

    static Object lambda13() {
        C0718runtime.setAndCoerceProperty$Ex(Lit69, Lit10, Lit70, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit69, Lit71, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit69, Lit72, Lit73, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit69, Lit74, "Bloqueio da Ignição", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit69, Lit75, Lit24, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit69, Lit47, Lit76, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit69, Lit56, Lit66, Lit12);
    }

    static Object lambda14() {
        C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit10, Lit80, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit45, Lit81, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit56, Lit66, Lit12);
    }

    static Object lambda15() {
        C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit10, Lit80, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit45, Lit81, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit79, Lit56, Lit66, Lit12);
    }

    static Object lambda16() {
        C0718runtime.setAndCoerceProperty$Ex(Lit84, Lit10, Lit85, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit84, Lit56, Lit86, Lit12);
    }

    static Object lambda17() {
        C0718runtime.setAndCoerceProperty$Ex(Lit84, Lit10, Lit85, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit84, Lit56, Lit86, Lit12);
    }

    static Object lambda18() {
        C0718runtime.setAndCoerceProperty$Ex(Lit89, Lit10, Lit90, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit89, Lit72, Lit91, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit89, Lit45, Lit92, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit89, Lit54, "bloquear.png", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit89, Lit74, "DESLIGADO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit89, Lit47, Lit93, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit89, Lit56, Lit94, Lit12);
    }

    static Object lambda19() {
        C0718runtime.setAndCoerceProperty$Ex(Lit89, Lit10, Lit90, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit89, Lit72, Lit91, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit89, Lit45, Lit92, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit89, Lit54, "bloquear.png", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit89, Lit74, "DESLIGADO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit89, Lit47, Lit93, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit89, Lit56, Lit94, Lit12);
    }

    public Object btn_bloquear_ignicao$Click() {
        C0718runtime.setThisForm();
        C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit97, C0718runtime.lookupGlobalVarInCurrentFormEnvironment(Lit3, C0718runtime.$Stthe$Mnnull$Mnvalue$St), Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit98, "#1234#stop", Lit9);
        return C0718runtime.callComponentMethod(Lit96, Lit99, LList.Empty, LList.Empty);
    }

    static Object lambda20() {
        C0718runtime.setAndCoerceProperty$Ex(Lit102, Lit10, Lit103, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit102, Lit56, Lit104, Lit12);
    }

    static Object lambda21() {
        C0718runtime.setAndCoerceProperty$Ex(Lit102, Lit10, Lit103, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit102, Lit56, Lit104, Lit12);
    }

    static Object lambda22() {
        C0718runtime.setAndCoerceProperty$Ex(Lit107, Lit45, Lit108, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit107, Lit54, "desbloquear-removebg-preview.png", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit107, Lit74, "LIGADO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit107, Lit47, Lit109, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit107, Lit56, Lit110, Lit12);
    }

    static Object lambda23() {
        C0718runtime.setAndCoerceProperty$Ex(Lit107, Lit45, Lit108, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit107, Lit54, "desbloquear-removebg-preview.png", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit107, Lit74, "LIGADO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit107, Lit47, Lit109, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit107, Lit56, Lit110, Lit12);
    }

    public Object btn_desbloquear_ignicao$Click() {
        C0718runtime.setThisForm();
        C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit97, C0718runtime.lookupGlobalVarInCurrentFormEnvironment(Lit3, C0718runtime.$Stthe$Mnnull$Mnvalue$St), Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit98, "#1234#ok", Lit9);
        return C0718runtime.callComponentMethod(Lit96, Lit99, LList.Empty, LList.Empty);
    }

    static Object lambda24() {
        C0718runtime.setAndCoerceProperty$Ex(Lit114, Lit10, Lit115, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit114, Lit45, Lit73, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit114, Lit56, Lit66, Lit12);
    }

    static Object lambda25() {
        C0718runtime.setAndCoerceProperty$Ex(Lit114, Lit10, Lit115, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit114, Lit45, Lit73, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit114, Lit56, Lit66, Lit12);
    }

    static Object lambda26() {
        C0718runtime.setAndCoerceProperty$Ex(Lit118, Lit10, Lit119, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit118, Lit56, Lit120, Lit12);
    }

    static Object lambda27() {
        C0718runtime.setAndCoerceProperty$Ex(Lit118, Lit10, Lit119, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit118, Lit56, Lit120, Lit12);
    }

    static Object lambda28() {
        C0718runtime.setAndCoerceProperty$Ex(Lit123, Lit71, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit123, Lit74, "Bloquear", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit123, Lit75, Lit24, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit123, Lit47, Lit124, Lit12);
    }

    static Object lambda29() {
        C0718runtime.setAndCoerceProperty$Ex(Lit123, Lit71, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit123, Lit74, "Bloquear", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit123, Lit75, Lit24, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit123, Lit47, Lit124, Lit12);
    }

    static Object lambda30() {
        C0718runtime.setAndCoerceProperty$Ex(Lit127, Lit10, Lit128, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit127, Lit56, Lit129, Lit12);
    }

    static Object lambda31() {
        C0718runtime.setAndCoerceProperty$Ex(Lit127, Lit10, Lit128, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit127, Lit56, Lit129, Lit12);
    }

    static Object lambda32() {
        C0718runtime.setAndCoerceProperty$Ex(Lit132, Lit10, Lit133, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit132, Lit71, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit132, Lit72, Lit91, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit132, Lit74, "Desbloquear", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit132, Lit75, Lit24, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit132, Lit47, Lit134, Lit12);
    }

    static Object lambda33() {
        C0718runtime.setAndCoerceProperty$Ex(Lit132, Lit10, Lit133, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit132, Lit71, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit132, Lit72, Lit91, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit132, Lit74, "Desbloquear", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit132, Lit75, Lit24, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit132, Lit47, Lit134, Lit12);
    }

    static Object lambda34() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit137, Lit10, Lit138, Lit12);
    }

    static Object lambda35() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit137, Lit10, Lit138, Lit12);
    }

    static Object lambda36() {
        C0718runtime.setAndCoerceProperty$Ex(Lit141, Lit71, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit141, Lit72, Lit73, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit141, Lit74, "Bloqueio da Bateria", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit141, Lit75, Lit24, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit141, Lit47, Lit142, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit141, Lit56, Lit66, Lit12);
    }

    static Object lambda37() {
        C0718runtime.setAndCoerceProperty$Ex(Lit141, Lit71, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit141, Lit72, Lit73, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit141, Lit74, "Bloqueio da Bateria", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit141, Lit75, Lit24, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit141, Lit47, Lit142, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit141, Lit56, Lit66, Lit12);
    }

    static Object lambda38() {
        C0718runtime.setAndCoerceProperty$Ex(Lit145, Lit10, Lit146, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit145, Lit45, Lit147, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit145, Lit56, Lit66, Lit12);
    }

    static Object lambda39() {
        C0718runtime.setAndCoerceProperty$Ex(Lit145, Lit10, Lit146, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit145, Lit45, Lit147, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit145, Lit56, Lit66, Lit12);
    }

    static Object lambda40() {
        C0718runtime.setAndCoerceProperty$Ex(Lit150, Lit10, Lit151, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit150, Lit56, Lit152, Lit12);
    }

    static Object lambda41() {
        C0718runtime.setAndCoerceProperty$Ex(Lit150, Lit10, Lit151, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit150, Lit56, Lit152, Lit12);
    }

    static Object lambda42() {
        C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit10, Lit156, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit72, Lit91, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit45, Lit157, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit54, "bloquear.png", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit74, "DESLIGADO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit47, Lit158, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit56, Lit159, Lit12);
    }

    static Object lambda43() {
        C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit10, Lit156, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit72, Lit91, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit45, Lit157, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit54, "bloquear.png", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit74, "DESLIGADO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit47, Lit158, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit56, Lit159, Lit12);
    }

    public Object btn_bloquear_bateria1$Click() {
        C0718runtime.setThisForm();
        if (C0718runtime.callYailPrimitive(C0718runtime.yail$Mnequal$Qu, LList.list2(C0718runtime.get$Mnproperty.apply2(Lit161, Lit74), "LIGADO"), Lit162, "=") == Boolean.FALSE) {
            return C0718runtime.callComponentMethod(Lit163, Lit164, LList.list1("Impossível realizar o comando"), Lit165);
        }
        C0718runtime.setAndCoerceProperty$Ex(Lit161, Lit74, "DESLIGADO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit74, "LIGADO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit97, "VAZIO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit98, "#1234#stop", Lit9);
        return C0718runtime.callComponentMethod(Lit96, Lit99, LList.Empty, LList.Empty);
    }

    static Object lambda44() {
        C0718runtime.setAndCoerceProperty$Ex(Lit168, Lit10, Lit169, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit168, Lit56, Lit170, Lit12);
    }

    static Object lambda45() {
        C0718runtime.setAndCoerceProperty$Ex(Lit168, Lit10, Lit169, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit168, Lit56, Lit170, Lit12);
    }

    static Object lambda46() {
        C0718runtime.setAndCoerceProperty$Ex(Lit161, Lit10, Lit173, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit161, Lit45, Lit174, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit161, Lit54, "desbloquear-removebg-preview.png", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit161, Lit74, "LIGADO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit161, Lit47, Lit175, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit161, Lit56, Lit176, Lit12);
    }

    static Object lambda47() {
        C0718runtime.setAndCoerceProperty$Ex(Lit161, Lit10, Lit173, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit161, Lit45, Lit174, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit161, Lit54, "desbloquear-removebg-preview.png", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit161, Lit74, "LIGADO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit161, Lit47, Lit175, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit161, Lit56, Lit176, Lit12);
    }

    public Object btn_desbloquear_bateria1$Click() {
        C0718runtime.setThisForm();
        if (C0718runtime.callYailPrimitive(C0718runtime.yail$Mnequal$Qu, LList.list2(C0718runtime.get$Mnproperty.apply2(Lit155, Lit74), "LIGADO"), Lit178, "=") == Boolean.FALSE) {
            return C0718runtime.callComponentMethod(Lit163, Lit164, LList.list1("Impossível realizar o comando"), Lit179);
        }
        C0718runtime.setAndCoerceProperty$Ex(Lit155, Lit74, "DESLIGADO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit161, Lit74, "LIGADO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit97, "VAZIO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit98, "#1234#ok", Lit9);
        return C0718runtime.callComponentMethod(Lit96, Lit99, LList.Empty, LList.Empty);
    }

    static Object lambda48() {
        C0718runtime.setAndCoerceProperty$Ex(Lit182, Lit10, Lit183, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit182, Lit45, Lit73, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit182, Lit56, Lit66, Lit12);
    }

    static Object lambda49() {
        C0718runtime.setAndCoerceProperty$Ex(Lit182, Lit10, Lit183, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit182, Lit45, Lit73, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit182, Lit56, Lit66, Lit12);
    }

    /* compiled from: Comandos.yail */
    public class frame extends ModuleBody {
        Comandos $main;

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 1:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 2:
                    if (!(obj instanceof Comandos)) {
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
                    if (!(obj instanceof Comandos)) {
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
                case 111:
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
                    if (!(obj instanceof Comandos)) {
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
                    if (!(obj instanceof Comandos)) {
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
                    Comandos comandos = this.$main;
                    try {
                        Component component = (Component) obj;
                        try {
                            String str = (String) obj2;
                            try {
                                if (obj3 == Boolean.FALSE) {
                                    z = false;
                                }
                                try {
                                    comandos.dispatchGenericEvent(component, str, z, (Object[]) obj4);
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
                case 111:
                    return this.$main.FirebaseDB1$GotValue(obj, obj2);
                default:
                    return super.apply2(moduleMethod, obj, obj2);
            }
        }

        public Object apply0(ModuleMethod moduleMethod) {
            switch (moduleMethod.selector) {
                case 18:
                    return Comandos.lambda2();
                case 19:
                    this.$main.$define();
                    return Values.empty;
                case 20:
                    return Comandos.lambda3();
                case 21:
                    return Comandos.lambda4();
                case 22:
                    return Comandos.lambda5();
                case 23:
                    return this.$main.Comandos$Initialize();
                case 24:
                    return Comandos.lambda6();
                case 25:
                    return Comandos.lambda7();
                case 26:
                    return Comandos.lambda8();
                case 27:
                    return Comandos.lambda9();
                case 28:
                    return this.$main.btn_voltarTela$Click();
                case 29:
                    return Comandos.lambda10();
                case 30:
                    return Comandos.lambda11();
                case 31:
                    return Comandos.lambda12();
                case 32:
                    return Comandos.lambda13();
                case 33:
                    return Comandos.lambda14();
                case 34:
                    return Comandos.lambda15();
                case 35:
                    return Comandos.lambda16();
                case 36:
                    return Comandos.lambda17();
                case 37:
                    return Comandos.lambda18();
                case 38:
                    return Comandos.lambda19();
                case 39:
                    return this.$main.btn_bloquear_ignicao$Click();
                case 40:
                    return Comandos.lambda20();
                case 41:
                    return Comandos.lambda21();
                case 42:
                    return Comandos.lambda22();
                case 43:
                    return Comandos.lambda23();
                case 44:
                    return this.$main.btn_desbloquear_ignicao$Click();
                case 45:
                    return Comandos.lambda24();
                case 46:
                    return Comandos.lambda25();
                case 47:
                    return Comandos.lambda26();
                case 48:
                    return Comandos.lambda27();
                case 49:
                    return Comandos.lambda28();
                case 50:
                    return Comandos.lambda29();
                case 51:
                    return Comandos.lambda30();
                case 52:
                    return Comandos.lambda31();
                case 53:
                    return Comandos.lambda32();
                case 54:
                    return Comandos.lambda33();
                case 55:
                    return Comandos.lambda34();
                case 56:
                    return Comandos.lambda35();
                case 57:
                    return Comandos.lambda36();
                case 58:
                    return Comandos.lambda37();
                case 59:
                    return Comandos.lambda38();
                case 60:
                    return Comandos.lambda39();
                case 61:
                    return Comandos.lambda40();
                case 62:
                    return Comandos.lambda41();
                case 63:
                    return Comandos.lambda42();
                case 64:
                    return Comandos.lambda43();
                case 65:
                    return this.$main.btn_bloquear_bateria1$Click();
                case 66:
                    return Comandos.lambda44();
                case 67:
                    return Comandos.lambda45();
                case 68:
                    return Comandos.lambda46();
                case 69:
                    return Comandos.lambda47();
                case 70:
                    return this.$main.btn_desbloquear_bateria1$Click();
                case 71:
                    return Comandos.lambda48();
                case 72:
                    return Comandos.lambda49();
                case 73:
                    return Comandos.lambda50();
                case 74:
                    return Comandos.lambda51();
                case 75:
                    return Comandos.lambda52();
                case 76:
                    return Comandos.lambda53();
                case 77:
                    return Comandos.lambda54();
                case 78:
                    return Comandos.lambda55();
                case 79:
                    return Comandos.lambda56();
                case 80:
                    return Comandos.lambda57();
                case 81:
                    return Comandos.lambda58();
                case 82:
                    return Comandos.lambda59();
                case 83:
                    return Comandos.lambda60();
                case 84:
                    return Comandos.lambda61();
                case 85:
                    return Comandos.lambda62();
                case 86:
                    return Comandos.lambda63();
                case 87:
                    return Comandos.lambda64();
                case 88:
                    return Comandos.lambda65();
                case 89:
                    return Comandos.lambda66();
                case 90:
                    return Comandos.lambda67();
                case 91:
                    return this.$main.btn_bloquear_combustivel$Click();
                case 92:
                    return Comandos.lambda68();
                case 93:
                    return Comandos.lambda69();
                case 94:
                    return Comandos.lambda70();
                case 95:
                    return Comandos.lambda71();
                case 96:
                    return this.$main.btn_desbloquear_combustivel$Click();
                case 97:
                    return Comandos.lambda72();
                case 98:
                    return Comandos.lambda73();
                case 99:
                    return Comandos.lambda74();
                case 100:
                    return Comandos.lambda75();
                case 101:
                    return Comandos.lambda76();
                case 102:
                    return Comandos.lambda77();
                case 103:
                    return Comandos.lambda78();
                case 104:
                    return Comandos.lambda79();
                case 105:
                    return Comandos.lambda80();
                case 106:
                    return Comandos.lambda81();
                case 107:
                    return Comandos.lambda82();
                case 108:
                    return Comandos.lambda83();
                case 109:
                    return Comandos.lambda84();
                case 110:
                    return Comandos.lambda85();
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
                case 73:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 74:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 75:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 76:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 77:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 78:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 79:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 80:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 81:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 82:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 83:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 84:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 85:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 86:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 87:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 88:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 89:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 90:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 91:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 92:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 93:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 94:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 95:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 96:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 97:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 98:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 99:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 100:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 101:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 102:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 103:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 104:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 105:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 106:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 107:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 108:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 109:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 110:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                default:
                    return super.match0(moduleMethod, callContext);
            }
        }
    }

    static Object lambda50() {
        C0718runtime.setAndCoerceProperty$Ex(Lit186, Lit10, Lit187, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit186, Lit56, Lit188, Lit12);
    }

    static Object lambda51() {
        C0718runtime.setAndCoerceProperty$Ex(Lit186, Lit10, Lit187, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit186, Lit56, Lit188, Lit12);
    }

    static Object lambda52() {
        C0718runtime.setAndCoerceProperty$Ex(Lit191, Lit10, Lit192, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit191, Lit71, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit191, Lit74, "Bloquear", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit191, Lit75, Lit24, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit191, Lit47, Lit193, Lit12);
    }

    static Object lambda53() {
        C0718runtime.setAndCoerceProperty$Ex(Lit191, Lit10, Lit192, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit191, Lit71, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit191, Lit74, "Bloquear", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit191, Lit75, Lit24, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit191, Lit47, Lit193, Lit12);
    }

    static Object lambda54() {
        C0718runtime.setAndCoerceProperty$Ex(Lit196, Lit10, Lit197, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit196, Lit56, Lit198, Lit12);
    }

    static Object lambda55() {
        C0718runtime.setAndCoerceProperty$Ex(Lit196, Lit10, Lit197, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit196, Lit56, Lit198, Lit12);
    }

    static Object lambda56() {
        C0718runtime.setAndCoerceProperty$Ex(Lit201, Lit10, Lit202, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit201, Lit71, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit201, Lit72, Lit91, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit201, Lit74, "Desbloquear", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit201, Lit75, Lit24, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit201, Lit47, Lit203, Lit12);
    }

    static Object lambda57() {
        C0718runtime.setAndCoerceProperty$Ex(Lit201, Lit10, Lit202, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit201, Lit71, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit201, Lit72, Lit91, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit201, Lit74, "Desbloquear", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit201, Lit75, Lit24, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit201, Lit47, Lit203, Lit12);
    }

    static Object lambda58() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit206, Lit10, Lit207, Lit12);
    }

    static Object lambda59() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit206, Lit10, Lit207, Lit12);
    }

    static Object lambda60() {
        C0718runtime.setAndCoerceProperty$Ex(Lit210, Lit71, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit210, Lit72, Lit73, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit210, Lit74, "Bloqueio da injeção de combustível", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit210, Lit75, Lit24, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit210, Lit47, Lit211, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit210, Lit56, Lit66, Lit12);
    }

    static Object lambda61() {
        C0718runtime.setAndCoerceProperty$Ex(Lit210, Lit71, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit210, Lit72, Lit73, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit210, Lit74, "Bloqueio da injeção de combustível", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit210, Lit75, Lit24, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit210, Lit47, Lit211, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit210, Lit56, Lit66, Lit12);
    }

    static Object lambda62() {
        C0718runtime.setAndCoerceProperty$Ex(Lit214, Lit10, Lit215, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit214, Lit45, Lit216, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit214, Lit56, Lit66, Lit12);
    }

    static Object lambda63() {
        C0718runtime.setAndCoerceProperty$Ex(Lit214, Lit10, Lit215, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit214, Lit45, Lit216, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit214, Lit56, Lit66, Lit12);
    }

    static Object lambda64() {
        C0718runtime.setAndCoerceProperty$Ex(Lit219, Lit10, Lit220, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit219, Lit56, Lit221, Lit12);
    }

    static Object lambda65() {
        C0718runtime.setAndCoerceProperty$Ex(Lit219, Lit10, Lit220, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit219, Lit56, Lit221, Lit12);
    }

    static Object lambda66() {
        C0718runtime.setAndCoerceProperty$Ex(Lit224, Lit10, Lit225, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit224, Lit72, Lit91, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit224, Lit45, Lit226, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit224, Lit54, "bloquear.png", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit224, Lit74, "DESLIGADO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit224, Lit47, Lit227, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit224, Lit56, Lit228, Lit12);
    }

    static Object lambda67() {
        C0718runtime.setAndCoerceProperty$Ex(Lit224, Lit10, Lit225, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit224, Lit72, Lit91, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit224, Lit45, Lit226, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit224, Lit54, "bloquear.png", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit224, Lit74, "DESLIGADO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit224, Lit47, Lit227, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit224, Lit56, Lit228, Lit12);
    }

    public Object btn_bloquear_combustivel$Click() {
        C0718runtime.setThisForm();
        if (C0718runtime.callYailPrimitive(C0718runtime.yail$Mnequal$Qu, LList.list2(C0718runtime.get$Mnproperty.apply2(Lit230, Lit74), "LIGADO"), Lit231, "=") == Boolean.FALSE) {
            return C0718runtime.callComponentMethod(Lit163, Lit164, LList.list1("Impossível realizar o comando"), Lit232);
        }
        C0718runtime.setAndCoerceProperty$Ex(Lit230, Lit74, "DESLIGADO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit224, Lit74, "LIGADO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit97, "VAZIO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit98, "#1234#stop", Lit9);
        return C0718runtime.callComponentMethod(Lit96, Lit99, LList.Empty, LList.Empty);
    }

    static Object lambda68() {
        C0718runtime.setAndCoerceProperty$Ex(Lit235, Lit10, Lit236, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit235, Lit56, Lit237, Lit12);
    }

    static Object lambda69() {
        C0718runtime.setAndCoerceProperty$Ex(Lit235, Lit10, Lit236, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit235, Lit56, Lit237, Lit12);
    }

    static Object lambda70() {
        C0718runtime.setAndCoerceProperty$Ex(Lit230, Lit10, Lit240, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit230, Lit45, Lit241, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit230, Lit54, "desbloquear-removebg-preview.png", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit230, Lit74, "LIGADO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit230, Lit47, Lit242, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit230, Lit56, Lit243, Lit12);
    }

    static Object lambda71() {
        C0718runtime.setAndCoerceProperty$Ex(Lit230, Lit10, Lit240, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit230, Lit45, Lit241, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit230, Lit54, "desbloquear-removebg-preview.png", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit230, Lit74, "LIGADO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit230, Lit47, Lit242, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit230, Lit56, Lit243, Lit12);
    }

    public Object btn_desbloquear_combustivel$Click() {
        C0718runtime.setThisForm();
        if (C0718runtime.callYailPrimitive(C0718runtime.yail$Mnequal$Qu, LList.list2(C0718runtime.get$Mnproperty.apply2(Lit224, Lit74), "LIGADO"), Lit245, "=") == Boolean.FALSE) {
            return C0718runtime.callComponentMethod(Lit163, Lit164, LList.list1("Impossível realizar o comando"), Lit246);
        }
        C0718runtime.setAndCoerceProperty$Ex(Lit224, Lit74, "DESLIGADO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit230, Lit74, "LIGADO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit97, "VAZIO", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit98, "#1234#ok", Lit9);
        return C0718runtime.callComponentMethod(Lit96, Lit99, LList.Empty, LList.Empty);
    }

    static Object lambda72() {
        C0718runtime.setAndCoerceProperty$Ex(Lit249, Lit10, Lit250, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit249, Lit45, Lit73, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit249, Lit56, Lit66, Lit12);
    }

    static Object lambda73() {
        C0718runtime.setAndCoerceProperty$Ex(Lit249, Lit10, Lit250, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit249, Lit45, Lit73, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit249, Lit56, Lit66, Lit12);
    }

    static Object lambda74() {
        C0718runtime.setAndCoerceProperty$Ex(Lit253, Lit10, Lit254, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit253, Lit56, Lit255, Lit12);
    }

    static Object lambda75() {
        C0718runtime.setAndCoerceProperty$Ex(Lit253, Lit10, Lit254, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit253, Lit56, Lit255, Lit12);
    }

    static Object lambda76() {
        C0718runtime.setAndCoerceProperty$Ex(Lit258, Lit10, Lit259, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit258, Lit71, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit258, Lit74, "Bloquear", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit258, Lit75, Lit24, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit258, Lit47, Lit260, Lit12);
    }

    static Object lambda77() {
        C0718runtime.setAndCoerceProperty$Ex(Lit258, Lit10, Lit259, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit258, Lit71, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit258, Lit74, "Bloquear", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit258, Lit75, Lit24, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit258, Lit47, Lit260, Lit12);
    }

    static Object lambda78() {
        C0718runtime.setAndCoerceProperty$Ex(Lit263, Lit10, Lit264, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit263, Lit56, Lit265, Lit12);
    }

    static Object lambda79() {
        C0718runtime.setAndCoerceProperty$Ex(Lit263, Lit10, Lit264, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit263, Lit56, Lit265, Lit12);
    }

    static Object lambda80() {
        C0718runtime.setAndCoerceProperty$Ex(Lit268, Lit10, Lit269, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit268, Lit71, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit268, Lit72, Lit91, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit268, Lit74, "Desbloquear", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit268, Lit75, Lit24, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit268, Lit47, Lit270, Lit12);
    }

    static Object lambda81() {
        C0718runtime.setAndCoerceProperty$Ex(Lit268, Lit10, Lit269, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit268, Lit71, Boolean.TRUE, Lit7);
        C0718runtime.setAndCoerceProperty$Ex(Lit268, Lit72, Lit91, Lit12);
        C0718runtime.setAndCoerceProperty$Ex(Lit268, Lit74, "Desbloquear", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit268, Lit75, Lit24, Lit12);
        return C0718runtime.setAndCoerceProperty$Ex(Lit268, Lit47, Lit270, Lit12);
    }

    static Object lambda82() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit275, Lit24, Lit12);
    }

    static Object lambda83() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit96, Lit275, Lit24, Lit12);
    }

    static Object lambda84() {
        C0718runtime.setAndCoerceProperty$Ex(Lit22, Lit278, "https://dazzling-fire-7140.firebaseio.com/", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit22, Lit279, "eva:tcctelemetria@gmail:com/", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit22, Lit280, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkIjp7InVpZCI6IjcyZTM0OTQ4LTcwMGYtNDMwMi04OWZhLWUzMWZjZmU4ZmZlNCIsInByb2plY3QiOiJFVkFfaW1vYmlsaXplciIsImRldmVsb3BlciI6ImV2YTp0Y2N0ZWxlbWV0cmlhQGdtYWlsOmNvbSJ9LCJ2IjowLCJleHAiOjE2NzUxNTM1NzQ2LCJpYXQiOjE2NjczMDUzNDZ9.iUTeiAG20ZkgyTJ5XzMErD4NpVbyViBxAIxOXW35FhM", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit22, Lit281, "https://unihawk-immobilizer-v1-default-rtdb.firebaseio.com/", Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit22, Lit282, "EVA_imobilizer", Lit9);
    }

    static Object lambda85() {
        C0718runtime.setAndCoerceProperty$Ex(Lit22, Lit278, "https://dazzling-fire-7140.firebaseio.com/", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit22, Lit279, "eva:tcctelemetria@gmail:com/", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit22, Lit280, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkIjp7InVpZCI6IjcyZTM0OTQ4LTcwMGYtNDMwMi04OWZhLWUzMWZjZmU4ZmZlNCIsInByb2plY3QiOiJFVkFfaW1vYmlsaXplciIsImRldmVsb3BlciI6ImV2YTp0Y2N0ZWxlbWV0cmlhQGdtYWlsOmNvbSJ9LCJ2IjowLCJleHAiOjE2NzUxNTM1NzQ2LCJpYXQiOjE2NjczMDUzNDZ9.iUTeiAG20ZkgyTJ5XzMErD4NpVbyViBxAIxOXW35FhM", Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit22, Lit281, "https://unihawk-immobilizer-v1-default-rtdb.firebaseio.com/", Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit22, Lit282, "EVA_imobilizer", Lit9);
    }

    public Object FirebaseDB1$GotValue(Object $tag, Object $value) {
        Object obj;
        Object obj2;
        C0718runtime.sanitizeComponentData($tag);
        Object $value2 = C0718runtime.sanitizeComponentData($value);
        C0718runtime.setThisForm();
        if (C0718runtime.callYailPrimitive(C0718runtime.yail$Mnequal$Qu, LList.list2(C0718runtime.callYailPrimitive(C0718runtime.yail$Mnlist$Mnget$Mnitem, LList.list2(C0718runtime.lookupGlobalVarInCurrentFormEnvironment(Lit5, C0718runtime.$Stthe$Mnnull$Mnvalue$St), Lit284), Lit285, "select list item"), Lit24), Lit286, "=") != Boolean.FALSE) {
            SimpleSymbol simpleSymbol = Lit3;
            ModuleMethod moduleMethod = C0718runtime.yail$Mnlist$Mnget$Mnitem;
            if ($value2 instanceof Package) {
                obj2 = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Lit287), " is not bound in the current context"), "Unbound Variable");
            } else {
                obj2 = $value2;
            }
            C0718runtime.addGlobalVarToCurrentFormEnvironment(simpleSymbol, C0718runtime.callYailPrimitive(moduleMethod, LList.list2(obj2, Lit288), Lit289, "select list item"));
        }
        if (C0718runtime.callYailPrimitive(C0718runtime.yail$Mnequal$Qu, LList.list2(C0718runtime.callYailPrimitive(C0718runtime.yail$Mnlist$Mnget$Mnitem, LList.list2(C0718runtime.lookupGlobalVarInCurrentFormEnvironment(Lit5, C0718runtime.$Stthe$Mnnull$Mnvalue$St), Lit284), Lit290, "select list item"), Lit284), Lit291, "=") != Boolean.FALSE) {
            SimpleSymbol simpleSymbol2 = Lit3;
            ModuleMethod moduleMethod2 = C0718runtime.yail$Mnlist$Mnget$Mnitem;
            if ($value2 instanceof Package) {
                obj = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Lit287), " is not bound in the current context"), "Unbound Variable");
            } else {
                obj = $value2;
            }
            C0718runtime.addGlobalVarToCurrentFormEnvironment(simpleSymbol2, C0718runtime.callYailPrimitive(moduleMethod2, LList.list2(obj, Lit292), Lit293, "select list item"));
        }
        if (C0718runtime.callYailPrimitive(C0718runtime.yail$Mnequal$Qu, LList.list2(C0718runtime.callYailPrimitive(C0718runtime.yail$Mnlist$Mnget$Mnitem, LList.list2(C0718runtime.lookupGlobalVarInCurrentFormEnvironment(Lit5, C0718runtime.$Stthe$Mnnull$Mnvalue$St), Lit284), Lit294, "select list item"), Lit295), Lit296, "=") == Boolean.FALSE) {
            return Values.empty;
        }
        SimpleSymbol simpleSymbol3 = Lit3;
        ModuleMethod moduleMethod3 = C0718runtime.yail$Mnlist$Mnget$Mnitem;
        if ($value2 instanceof Package) {
            $value2 = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Lit287), " is not bound in the current context"), "Unbound Variable");
        }
        return C0718runtime.addGlobalVarToCurrentFormEnvironment(simpleSymbol3, C0718runtime.callYailPrimitive(moduleMethod3, LList.list2($value2, Lit297), Lit298, "select list item"));
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
        Comandos = this;
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
