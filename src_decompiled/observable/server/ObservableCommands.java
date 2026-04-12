/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.Command
 *  com.mojang.brigadier.CommandDispatcher
 *  com.mojang.brigadier.arguments.ArgumentType
 *  com.mojang.brigadier.arguments.BoolArgumentType
 *  com.mojang.brigadier.arguments.IntegerArgumentType
 *  com.mojang.brigadier.arguments.StringArgumentType
 *  com.mojang.brigadier.builder.ArgumentBuilder
 *  com.mojang.brigadier.builder.LiteralArgumentBuilder
 *  com.mojang.brigadier.builder.RequiredArgumentBuilder
 *  com.mojang.brigadier.context.CommandContext
 *  kotlin.Metadata
 *  kotlin.collections.CollectionsKt
 *  kotlin.collections.SetsKt
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.Charsets
 *  kotlinx.serialization.SerializationStrategy
 *  kotlinx.serialization.json.Json
 *  net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.commands.CommandBuildContext
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.commands.Commands
 *  net.minecraft.commands.Commands$CommandSelection
 *  net.minecraft.commands.arguments.DimensionArgument
 *  net.minecraft.commands.arguments.GameProfileArgument
 *  net.minecraft.commands.arguments.coordinates.Vec3Argument
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.protocol.common.custom.CustomPacketPayload
 *  net.minecraft.resources.Identifier
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.permissions.Permissions
 *  net.minecraft.server.players.NameAndId
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.PositionMoveRotation
 *  net.minecraft.world.phys.Vec3
 *  org.jetbrains.annotations.NotNull
 */
package observable.server;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import java.lang.invoke.LambdaMetafactory;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.json.Json;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.DimensionArgument;
import net.minecraft.commands.arguments.GameProfileArgument;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.server.players.NameAndId;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PositionMoveRotation;
import net.minecraft.world.phys.Vec3;
import observable.Observable;
import observable.net.BetterChannel;
import observable.net.S2CPacket;
import observable.server.ServerSettingsData;
import observable.server.ServerSettingsKt;
import observable.util.ConstantsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u001e\u0010\u0006\u001a\u00020\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002\u00a8\u0006\f"}, d2={"Lobservable/server/ObservableCommands;", "", "<init>", "()V", "register", "", "teleport", "ctx", "Lcom/mojang/brigadier/context/CommandContext;", "Lnet/minecraft/commands/CommandSourceStack;", "pos", "Lnet/minecraft/world/phys/Vec3;", "observable"})
@SourceDebugExtension(value={"SMAP\nCommands.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Commands.kt\nobservable/server/ObservableCommands\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 BetterChannel.kt\nobservable/net/BetterChannel\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,173:1\n1915#2:174\n1915#2:178\n1916#2:183\n1916#2:185\n1915#2:186\n1915#2:190\n1916#2:195\n1916#2:197\n93#3:175\n85#3,2:176\n87#3,4:179\n91#3:184\n93#3:187\n85#3,2:188\n87#3,4:191\n91#3:196\n13471#4,3:198\n*S KotlinDebug\n*F\n+ 1 Commands.kt\nobservable/server/ObservableCommands\n*L\n53#1:174\n56#1:178\n56#1:183\n53#1:185\n68#1:186\n71#1:190\n71#1:195\n68#1:197\n56#1:175\n56#1:176,2\n56#1:179,4\n56#1:184\n71#1:187\n71#1:188,2\n71#1:191,4\n71#1:196\n81#1:198,3\n*E\n"})
public final class ObservableCommands {
    @NotNull
    public static final ObservableCommands INSTANCE = new ObservableCommands();

    private ObservableCommands() {
    }

    public final void register() {
        CommandRegistrationCallback.EVENT.register(ObservableCommands::register$lambda$0);
    }

    private final void teleport(CommandContext<CommandSourceStack> ctx, Vec3 pos) {
        ServerPlayer serverPlayer = ((CommandSourceStack)ctx.getSource()).getPlayerOrException();
        Intrinsics.checkNotNullExpressionValue((Object)serverPlayer, (String)"getPlayerOrException(...)");
        ServerPlayer player = serverPlayer;
        ServerLevel serverLevel = DimensionArgument.getDimension(ctx, (String)"dim");
        Intrinsics.checkNotNullExpressionValue((Object)serverLevel, (String)"getDimension(...)");
        ServerLevel level = serverLevel;
        PositionMoveRotation rotation = new PositionMoveRotation(pos, Vec3.ZERO, 0.0f, 0.0f);
        player.teleportTo(pos.x, pos.y, pos.z);
        ServerLevel serverLevel2 = player.level();
        Intrinsics.checkNotNull((Object)serverLevel2, (String)"null cannot be cast to non-null type net.minecraft.server.level.ServerLevel");
        if (Intrinsics.areEqual((Object)level, (Object)serverLevel2)) {
            player.connection.teleport(rotation, SetsKt.emptySet());
        } else {
            player.teleportTo(level, pos.x, pos.y, pos.z, SetsKt.emptySet(), 0.0f, 0.0f, false);
        }
        Observable.INSTANCE.getLOGGER().info("Moved " + player.getGameProfile().name() + " to (" + pos.x + ", " + pos.y + ", " + pos.z + ") in " + level);
    }

    private static final Component register$lambda$0$1$0() {
        Object[] objectArray = new Object[]{ConstantsKt.getMOD_URL_COMPONENT()};
        return (Component)Component.translatable((String)"text.observable.cmd", (Object[])objectArray);
    }

    private static final Component register$lambda$0$2$0() {
        return (Component)Component.literal((String)ServerSettingsKt.getServerSettings().toString());
    }

    private static final Component register$lambda$0$3$0(int $duration) {
        Object[] objectArray = new Object[]{$duration};
        return (Component)Component.translatable((String)"text.observable.profile_started", (Object[])objectArray);
    }

    private static final int register$lambda$0$6$0$0(Field $field, CommandContext ctx) {
        int n;
        try {
            $field.setAccessible(true);
            $field.set(ServerSettingsKt.getServerSettings(), ctx.getArgument("newVal", $field.getType()));
            ServerSettingsKt.getServerSettings().sync();
            n = 1;
        }
        catch (Exception e) {
            e.printStackTrace();
            ((CommandSourceStack)ctx.getSource()).sendFailure((Component)Component.literal((String)("Error setting value\n" + e)));
            n = 0;
        }
        return n;
    }

    private static final boolean register$lambda$0$0(CommandSourceStack it) {
        return it.permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER);
    }

    private static final int register$lambda$0$1(CommandContext ctx) {
        ((CommandSourceStack)ctx.getSource()).sendSuccess(ObservableCommands::register$lambda$0$1$0, false);
        return 1;
    }

    private static final int register$lambda$0$2(CommandContext ctx) {
        ((CommandSourceStack)ctx.getSource()).sendSuccess(ObservableCommands::register$lambda$0$2$0, false);
        return 1;
    }

    private static final int register$lambda$0$3(CommandContext ctx) {
        int duration = IntegerArgumentType.getInteger((CommandContext)ctx, (String)"duration");
        Observable.INSTANCE.getPROFILER().runWithDuration(((CommandSourceStack)ctx.getSource()).getPlayer(), duration, false);
        ((CommandSourceStack)ctx.getSource()).sendSuccess(() -> ObservableCommands.register$lambda$0$3$0(duration), false);
        return 1;
    }

    /*
     * WARNING - void declaration
     */
    private static final int register$lambda$0$4(CommandContext ctx) {
        Collection collection = GameProfileArgument.getGameProfiles((CommandContext)ctx, (String)"player");
        Intrinsics.checkNotNullExpressionValue((Object)collection, (String)"getGameProfiles(...)");
        Iterable $this$forEach$iv = collection;
        boolean $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            void players$iv$iv;
            void this_$iv$iv;
            void player$iv;
            void this_$iv;
            NameAndId player = (NameAndId)element$iv;
            boolean bl = false;
            Set<String> set = ServerSettingsKt.getServerSettings().getAllowedPlayers();
            String string = player.id().toString();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
            set.add(string);
            MinecraftServer minecraftServer = Observable.INSTANCE.getSERVER_INSTANCE();
            if (minecraftServer == null || (minecraftServer = minecraftServer.getPlayerList()) == null || (minecraftServer = minecraftServer.getPlayer(player.id())) == null) continue;
            MinecraftServer it = minecraftServer;
            boolean bl2 = false;
            BetterChannel betterChannel = Observable.INSTANCE.getCHANNEL();
            MinecraftServer minecraftServer2 = it;
            S2CPacket.Availability msg$iv = S2CPacket.Availability.Available;
            boolean $i$f$sendToPlayer = false;
            void var13_13 = this_$iv;
            Iterable iterable = CollectionsKt.listOf((Object)player$iv);
            S2CPacket.Availability msg$iv$iv = msg$iv;
            boolean $i$f$sendToPlayers = false;
            String string2 = S2CPacket.Availability.class.getName();
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getName(...)");
            byte[] byArray = Json.Default.encodeToString((SerializationStrategy)S2CPacket.Availability.Companion.serializer(), (Object)msg$iv$iv).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"getBytes(...)");
            BetterChannel.SerializedPayload payload$iv$iv = new BetterChannel.SerializedPayload(string2, byArray, this_$iv$iv.getS2cLocation());
            void $this$forEach$iv$iv$iv = players$iv$iv;
            boolean $i$f$forEach2 = false;
            for (Object element$iv$iv$iv : $this$forEach$iv$iv$iv) {
                ServerPlayer player$iv$iv = (ServerPlayer)element$iv$iv$iv;
                boolean bl3 = false;
                if (!ServerPlayNetworking.canSend((ServerPlayer)player$iv$iv, (Identifier)this_$iv$iv.getS2cLocation())) continue;
                ServerPlayNetworking.send((ServerPlayer)player$iv$iv, (CustomPacketPayload)payload$iv$iv);
            }
        }
        ServerSettingsKt.getServerSettings().sync();
        return 1;
    }

    /*
     * WARNING - void declaration
     */
    private static final int register$lambda$0$5(CommandContext ctx) {
        Collection collection = GameProfileArgument.getGameProfiles((CommandContext)ctx, (String)"player");
        Intrinsics.checkNotNullExpressionValue((Object)collection, (String)"getGameProfiles(...)");
        Iterable $this$forEach$iv = collection;
        boolean $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            void players$iv$iv;
            void this_$iv$iv;
            void player$iv;
            void this_$iv;
            NameAndId player = (NameAndId)element$iv;
            boolean bl = false;
            ServerSettingsKt.getServerSettings().getAllowedPlayers().remove(player.id().toString());
            MinecraftServer minecraftServer = Observable.INSTANCE.getSERVER_INSTANCE();
            if (minecraftServer == null || (minecraftServer = minecraftServer.getPlayerList()) == null || (minecraftServer = minecraftServer.getPlayer(player.id())) == null) continue;
            MinecraftServer it = minecraftServer;
            boolean bl2 = false;
            BetterChannel betterChannel = Observable.INSTANCE.getCHANNEL();
            MinecraftServer minecraftServer2 = it;
            S2CPacket.Availability msg$iv = S2CPacket.Availability.NoPermissions;
            boolean $i$f$sendToPlayer = false;
            void var13_13 = this_$iv;
            Iterable iterable = CollectionsKt.listOf((Object)player$iv);
            S2CPacket.Availability msg$iv$iv = msg$iv;
            boolean $i$f$sendToPlayers = false;
            String string = S2CPacket.Availability.class.getName();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getName(...)");
            byte[] byArray = Json.Default.encodeToString((SerializationStrategy)S2CPacket.Availability.Companion.serializer(), (Object)msg$iv$iv).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"getBytes(...)");
            BetterChannel.SerializedPayload payload$iv$iv = new BetterChannel.SerializedPayload(string, byArray, this_$iv$iv.getS2cLocation());
            void $this$forEach$iv$iv$iv = players$iv$iv;
            boolean $i$f$forEach2 = false;
            for (Object element$iv$iv$iv : $this$forEach$iv$iv$iv) {
                ServerPlayer player$iv$iv = (ServerPlayer)element$iv$iv$iv;
                boolean bl3 = false;
                if (!ServerPlayNetworking.canSend((ServerPlayer)player$iv$iv, (Identifier)this_$iv$iv.getS2cLocation())) continue;
                ServerPlayNetworking.send((ServerPlayer)player$iv$iv, (CustomPacketPayload)payload$iv$iv);
            }
        }
        ServerSettingsKt.getServerSettings().sync();
        return 1;
    }

    private static final int register$lambda$0$7(CommandContext ctx) {
        ServerLevel serverLevel = DimensionArgument.getDimension((CommandContext)ctx, (String)"dim");
        Intrinsics.checkNotNullExpressionValue((Object)serverLevel, (String)"getDimension(...)");
        ServerLevel level = serverLevel;
        int id = IntegerArgumentType.getInteger((CommandContext)ctx, (String)"id");
        Entity entity = level.getEntity(id);
        if (entity == null || (entity = entity.position()) == null) {
            ObservableCommands $this$register_u24lambda_u240_u247_u240 = INSTANCE;
            boolean bl = false;
            ((CommandSourceStack)ctx.getSource()).sendFailure((Component)Component.translatable((String)"text.observable.entity_not_found"));
            return 0;
        }
        Entity pos = entity;
        Intrinsics.checkNotNull((Object)ctx);
        INSTANCE.teleport((CommandContext<CommandSourceStack>)ctx, (Vec3)pos);
        return 1;
    }

    private static final int register$lambda$0$8(CommandContext ctx) {
        Intrinsics.checkNotNull((Object)ctx);
        Vec3 vec3 = Vec3Argument.getVec3((CommandContext)ctx, (String)"pos");
        Intrinsics.checkNotNullExpressionValue((Object)vec3, (String)"getVec3(...)");
        INSTANCE.teleport((CommandContext<CommandSourceStack>)ctx, vec3);
        return 1;
    }

    private static final int register$lambda$0$9(CommandContext ctx) {
        ServerSettingsKt.resetSettings();
        return 1;
    }

    /*
     * Unable to fully structure code
     */
    private static final void register$lambda$0(CommandDispatcher dispatcher, CommandBuildContext var1_1, Commands.CommandSelection var2_2) {
        Intrinsics.checkNotNullParameter((Object)dispatcher, (String)"dispatcher");
        Intrinsics.checkNotNullParameter((Object)var1_1, (String)"<unused var>");
        Intrinsics.checkNotNullParameter((Object)var2_2, (String)"<unused var>");
        var3_3 = Commands.literal((String)"set");
        var18_4 = (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal((String)"observable").requires((Predicate<CommandSourceStack>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, register$lambda$0$0(net.minecraft.commands.CommandSourceStack ), (Lnet/minecraft/commands/CommandSourceStack;)Z)())).executes((Command)LambdaMetafactory.metafactory(null, null, null, (Lcom/mojang/brigadier/context/CommandContext;)I, register$lambda$0$1(com.mojang.brigadier.context.CommandContext ), (Lcom/mojang/brigadier/context/CommandContext;)I)())).then(Commands.literal((String)"config").executes((Command)LambdaMetafactory.metafactory(null, null, null, (Lcom/mojang/brigadier/context/CommandContext;)I, register$lambda$0$2(com.mojang.brigadier.context.CommandContext ), (Lcom/mojang/brigadier/context/CommandContext;)I)()))).then(Commands.literal((String)"run").then(Commands.argument((String)"duration", (ArgumentType)((ArgumentType)IntegerArgumentType.integer())).executes((Command)LambdaMetafactory.metafactory(null, null, null, (Lcom/mojang/brigadier/context/CommandContext;)I, register$lambda$0$3(com.mojang.brigadier.context.CommandContext ), (Lcom/mojang/brigadier/context/CommandContext;)I)())))).then(Commands.literal((String)"allow").then(Commands.argument((String)"player", (ArgumentType)((ArgumentType)GameProfileArgument.gameProfile())).executes((Command)LambdaMetafactory.metafactory(null, null, null, (Lcom/mojang/brigadier/context/CommandContext;)I, register$lambda$0$4(com.mojang.brigadier.context.CommandContext ), (Lcom/mojang/brigadier/context/CommandContext;)I)())))).then(Commands.literal((String)"deny").then(Commands.argument((String)"player", (ArgumentType)((ArgumentType)GameProfileArgument.gameProfile())).executes((Command)LambdaMetafactory.metafactory(null, null, null, (Lcom/mojang/brigadier/context/CommandContext;)I, register$lambda$0$5(com.mojang.brigadier.context.CommandContext ), (Lcom/mojang/brigadier/context/CommandContext;)I)())));
        var17_5 = dispatcher;
        $i$a$-let-ObservableCommands$register$1$7 = false;
        v0 = ServerSettingsData.class.getDeclaredFields();
        Intrinsics.checkNotNullExpressionValue((Object)v0, (String)"getDeclaredFields(...)");
        var5_7 = v0;
        initial$iv = it;
        $i$f$fold = false;
        accumulator$iv = initial$iv;
        for (void element$iv : $this$fold$iv) {
            block4: {
                block3: {
                    var12_14 = (Field)element$iv;
                    setCmd = accumulator$iv;
                    $i$a$-fold-ObservableCommands$register$1$7$1 = false;
                    var15_17 = field.getType();
                    if (!Intrinsics.areEqual(var15_17, Integer.TYPE)) break block3;
                    v1 = (ArgumentType)IntegerArgumentType.integer();
                    ** GOTO lbl31
                }
                if (!Intrinsics.areEqual(var15_17, Boolean.TYPE)) break block4;
                v1 = (ArgumentType)BoolArgumentType.bool();
                ** GOTO lbl31
            }
            if (!Intrinsics.areEqual(var15_17, String.class)) {
                v2 = setCmd;
            } else {
                v1 = (ArgumentType)StringArgumentType.string();
lbl31:
                // 3 sources

                argType = v1;
                v2 = (LiteralArgumentBuilder)setCmd.then(Commands.literal((String)field.getName()).then(Commands.argument((String)"newVal", (ArgumentType)argType).executes((Command)LambdaMetafactory.metafactory(null, null, null, (Lcom/mojang/brigadier/context/CommandContext;)I, register$lambda$0$6$0$0(java.lang.reflect.Field com.mojang.brigadier.context.CommandContext ), (Lcom/mojang/brigadier/context/CommandContext;)I)((Field)field))));
            }
            accumulator$iv = v2;
        }
        var19_19 = accumulator$iv;
        var17_5.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)var18_4.then((ArgumentBuilder)var19_19)).then(Commands.literal((String)"tp").then(((RequiredArgumentBuilder)Commands.argument((String)"dim", (ArgumentType)((ArgumentType)DimensionArgument.dimension())).then(Commands.literal((String)"entity").then(Commands.argument((String)"id", (ArgumentType)((ArgumentType)IntegerArgumentType.integer())).executes((Command)LambdaMetafactory.metafactory(null, null, null, (Lcom/mojang/brigadier/context/CommandContext;)I, register$lambda$0$7(com.mojang.brigadier.context.CommandContext ), (Lcom/mojang/brigadier/context/CommandContext;)I)())))).then(Commands.literal((String)"position").then(Commands.argument((String)"pos", (ArgumentType)((ArgumentType)Vec3Argument.vec3())).executes((Command)LambdaMetafactory.metafactory(null, null, null, (Lcom/mojang/brigadier/context/CommandContext;)I, register$lambda$0$8(com.mojang.brigadier.context.CommandContext ), (Lcom/mojang/brigadier/context/CommandContext;)I)())))))).then(Commands.literal((String)"reset").executes((Command)LambdaMetafactory.metafactory(null, null, null, (Lcom/mojang/brigadier/context/CommandContext;)I, register$lambda$0$9(com.mojang.brigadier.context.CommandContext ), (Lcom/mojang/brigadier/context/CommandContext;)I)())));
    }
}

